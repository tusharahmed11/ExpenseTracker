package info.imtushar.expensetracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


@Serializable
object ExpensesRoute

@Serializable
object ReportsRoute

@Serializable
object AddRoute

@Serializable
object SettingsRoute


sealed class NavDestination(val title: String, val route: Any, val icon: ImageVector) {
    object Expenses : NavDestination(
        title = "Expenses",
        route = ExpensesRoute,
        icon = Icons.Filled.Home
    )
    object Reports : NavDestination(
        title = "Reports",
        route = ReportsRoute,
        icon = Icons.Default.DateRange // Changed to a more appropriate icon for reports
    )
    object Add : NavDestination(
        title = "Add",
        route = AddRoute,
        icon = Icons.Filled.Add // Changed to a plus icon for adding
    )
    object Settings : NavDestination(
        title = "Settings",
        route = SettingsRoute,
        icon = Icons.Filled.Settings // Changed to a settings icon
    )
}