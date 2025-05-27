package info.imtushar.expensetracker

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import info.imtushar.expensetracker.navigation.NavigationHost
import info.imtushar.expensetracker.screens.BottomNavigationBar
import info.imtushar.expensetracker.ui.theme.BackgroundElevated
import info.imtushar.expensetracker.ui.theme.ExpenseTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            val view = LocalView.current
            val window = (view.context as? Activity)?.window

            val appBarBackgroundColor = MaterialTheme.colorScheme.primary // Or your custom color

            val useDarkIcons = !isColorDark(appBarBackgroundColor) // Helper function below

            if (!view.isInEditMode && window != null) { // Check if in Edit Mode and window is not null
                SideEffect {
                    // Set status bar color
                    window.statusBarColor = BackgroundElevated.toArgb()

                    // Control icon appearance (light/dark)
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkIcons

                    // Optional: Control navigation bar color and icons
                    // window.navigationBarColor = appBarBackgroundColor.toArgb() // Or another color
                    // WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = useDarkIcons
                }
            }



            ExpenseTrackerTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { innerPadding ->
                    NavigationHost(
                        navController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

// Helper function to determine if a color is "dark"
// This is a simple heuristic, you might want a more sophisticated one.
fun isColorDark(color: Color): Boolean {
    val red = color.red * 255
    val green = color.green * 255
    val blue = color.blue * 255
    // Formula for luminance
    val luminance = (0.2126 * red + 0.7152 * green + 0.0722 * blue) / 255
    return luminance < 0.5 // Threshold can be adjusted
}
