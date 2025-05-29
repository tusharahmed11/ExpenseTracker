package info.imtushar.expensetracker.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imtushar.expensetracker.data.models.Category
import info.imtushar.expensetracker.data.models.Expense
import info.imtushar.expensetracker.repository.CategoryRepository
import info.imtushar.expensetracker.repository.ExpenseRepository
import info.imtushar.expensetracker.utils.FilterType
import info.imtushar.expensetracker.utils.Recurrence
import info.imtushar.expensetracker.utils.toReadableFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(private val expenseRepository: ExpenseRepository, private val categoryRepository: CategoryRepository) : ViewModel() {

    var amountValue by mutableStateOf("")
    var noteValue by mutableStateOf("")
    var titleValue by mutableStateOf("")
    var selectedRecurrence by mutableStateOf<Recurrence?>(null)
    var selectedCategory by mutableStateOf<Category?>(null)
    var selectedDate by mutableStateOf<LocalDate>(LocalDate.now())
    var selectedTime by mutableStateOf<LocalTime>(LocalTime.now())

    var selectedFilter by mutableStateOf<FilterType>(FilterType.THIS_WEEK)


    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses.asStateFlow()

    // Convert selectedFilter (MutableState) to a Flow
    private val selectedFilterFlow: Flow<FilterType> = snapshotFlow { selectedFilter }

    init {
        fetchCategories()
        fetchExpenses()
    }

    // Grouped expenses for display (e.g., by day for THIS_WEEK/THIS_MONTH, by month for THIS_YEAR)
    val groupedExpenses: StateFlow<Map<String, Pair<List<Expense>, Double>>> = combine(
        expenses,
        selectedFilterFlow
    ) { expenseList, filter ->
        val today = LocalDate.now() // May 29, 2025
        val filteredExpenses = when (filter) {
            FilterType.THIS_WEEK -> {
                val startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))
                expenseList.filter { !it.date.isBefore(startOfWeek) && !it.date.isAfter(today) }
            }
            FilterType.THIS_MONTH -> {
                val startOfMonth = today.withDayOfMonth(1)
                expenseList.filter { !it.date.isBefore(startOfMonth) && !it.date.isAfter(today) }
            }
            FilterType.THIS_YEAR -> {
                val startOfYear = today.withDayOfYear(1)
                expenseList.filter { !it.date.isBefore(startOfYear) && !it.date.isAfter(today) }
            }
        }

        when (filter) {
            FilterType.THIS_WEEK, FilterType.THIS_MONTH -> {
                filteredExpenses.groupBy { expense ->
                    val daysBetween = java.time.Period.between(expense.date, today).days
                    when (daysBetween) {
                        0 -> "Today"
                        1 -> "Yesterday"
                        else -> expense.date.dayOfWeek.name.toReadableFilter()
                    }
                }.mapValues { entry ->
                    val expenses = entry.value.sortedByDescending { it.time }
                    val total = expenses.sumOf { it.amount }
                    expenses to total
                }
            }
            FilterType.THIS_YEAR -> {
                val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
                filteredExpenses.groupBy { expense ->
                    expense.date.format(formatter) // e.g., "May 2025"
                }.mapValues { entry ->
                    val expenses = entry.value.sortedByDescending { it.date }
                    val total = expenses.sumOf { it.amount }
                    expenses to total
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    // Total amount for the selected filter
    val totalAmount: StateFlow<Double> = combine(
        expenses,
        selectedFilterFlow
    ) { expenseList, filter ->
        val today = LocalDate.now()
        val filteredExpenses = when (filter) {
            FilterType.THIS_WEEK -> {
                val startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY))
                expenseList.filter { !it.date.isBefore(startOfWeek) && !it.date.isAfter(today) }
            }
            FilterType.THIS_MONTH -> {
                val startOfMonth = today.withDayOfMonth(1)
                expenseList.filter { !it.date.isBefore(startOfMonth) && !it.date.isAfter(today) }
            }
            FilterType.THIS_YEAR -> {
                val startOfYear = today.withDayOfYear(1)
                expenseList.filter { !it.date.isBefore(startOfYear) && !it.date.isAfter(today) }
            }
        }
        filteredExpenses.sumOf { it.amount }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )


    private fun fetchExpenses() {
        viewModelScope.launch {
            expenseRepository.getAllExpenses().collect { expenseList ->
                _expenses.value = expenseList
            }
        }
    }


    private fun fetchCategories() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collect { categoryList ->
                _categories.value = categoryList
            }
        }
    }

    fun addExpense(onValidationResult: (String) -> Unit) {

        if (amountValue.isBlank()) {
            onValidationResult("Amount cannot be empty. Please enter a valid amount.")
            return
        }

        val amount = amountValue.toDoubleOrNull()
        if (amount == null) {
            onValidationResult("Amount must be a valid decimal number (e.g., 10.50).")
            return
        }

        if (amount <= 0) {
            onValidationResult("Amount must be greater than zero.")
            return
        }

        if (titleValue.isBlank()) {
            onValidationResult("Title cannot be empty. Please provide a title for the expense.")
            return
        }

        if (selectedCategory == null) {
            onValidationResult("Category cannot be empty. Please select a category.")
            return
        }


        // All validations passed, proceed to add the expense
        viewModelScope.launch(Dispatchers.IO) {
            val recurrence = selectedRecurrence ?: Recurrence.NONE
            val expense = Expense(
                title = titleValue,
                amount = amount,
                note = noteValue,
                recurrence = recurrence,
                categoryId = selectedCategory!!.id, // Safe to use !! since we validated
                date = selectedDate,
                time = selectedTime
            )
            expenseRepository.addExpense(expense)

            withContext(Dispatchers.Main) {
                // Clear the form after successful addition
                amountValue = ""
                noteValue = ""
                titleValue = ""
                selectedRecurrence = null
                selectedCategory = null
                selectedDate = LocalDate.now()
                selectedTime = LocalTime.now()

                onValidationResult("") // Empty string indicates success
            }
        }
    }

}