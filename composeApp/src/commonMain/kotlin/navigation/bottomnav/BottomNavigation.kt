package navigation.bottomnav

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import navigation.MainDestinations

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            path = MainDestinations.HomeScreen.path,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Default.Home
        ),
        BottomNavigationItem(
            title = "Payment",
            path = MainDestinations.PaymentScreen.path,
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Default.Add,
        ),
        BottomNavigationItem(
            title = "Profile",
            path = MainDestinations.ProfileScreen.path,
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Default.Person
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar(
        modifier = Modifier.height(80.dp)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.path)
                },
                icon = {
                    Icon(
                        imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val path: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)