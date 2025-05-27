package info.imtushar.expensetracker.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import info.imtushar.expensetracker.navigation.NavDestination
import info.imtushar.expensetracker.navigation.NavigationHost
import info.imtushar.expensetracker.ui.theme.BackgroundElevated
import info.imtushar.expensetracker.ui.theme.PrimaryVariant

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {

    var selectedIndex by remember { mutableIntStateOf(0) }

    val destinations = listOf(
        NavDestination.Expenses,
        NavDestination.Reports,
        NavDestination.Add,
        NavDestination.Settings
    )
    NavigationBar(
        containerColor = BackgroundElevated
    ) {
        destinations.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = null)
                },
                label = { Text(screen.title) },
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}