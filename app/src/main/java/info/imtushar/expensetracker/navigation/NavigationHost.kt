package info.imtushar.expensetracker.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import info.imtushar.expensetracker.screens.AddScreen
import info.imtushar.expensetracker.screens.expenses.ExpensesScreen
import info.imtushar.expensetracker.screens.ReportsScreen
import info.imtushar.expensetracker.screens.settings.CategoryScreen
import info.imtushar.expensetracker.screens.settings.SettingsScreen


@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = ExpensesRoute,
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(innerPadding)
    ){
        composable<ExpensesRoute>{
            ExpensesScreen()
        }
        composable<ReportsRoute>{
            ReportsScreen()
        }
        composable<AddRoute>{
            AddScreen()
        }
        composable<SettingsRoute>{
            SettingsScreen(
                onCategoryAction = {
                    navController.navigate(CategoryRoute)
                }
            )

        }

        composable<CategoryRoute>{
            CategoryScreen(
                onBackAction = {
                    navController.navigateUp()
                }
            )
        }

    }

}