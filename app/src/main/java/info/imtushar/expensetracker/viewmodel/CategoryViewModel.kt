package info.imtushar.expensetracker.viewmodel

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.delete
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imtushar.expensetracker.data.models.Category
import info.imtushar.expensetracker.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository): ViewModel()  {

    val categoryTextFieldState = TextFieldState()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collect { categoryList ->
                _categories.value = categoryList
            }
        }
    }

    fun addCategory(color: String) {
        if (categoryTextFieldState.text.isNotBlank()) {
            viewModelScope.launch {
                val category = Category(
                    name = categoryTextFieldState.text.toString(),
                    color = color // Pass the color, e.g., "#BF5AF2"
                )
                categoryRepository.insert(category)
                categoryTextFieldState.edit { delete(0, length) } // Clear the text field
            }
        }
    }

    fun deleteCategory(categoryId: UUID) {
        viewModelScope.launch {
            categoryRepository.delete(categoryId)
        }
    }


}