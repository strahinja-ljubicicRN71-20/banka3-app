package ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import navigation.BottomNavGraph
import navigation.bottomnav.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val bottomBarNavController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "TopBar")
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = bottomBarNavController)
        },
        content = {
            BottomNavGraph(navController = bottomBarNavController)
        }
    )
}

