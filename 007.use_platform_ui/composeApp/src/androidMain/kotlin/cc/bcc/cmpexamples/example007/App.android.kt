package cc.bcc.cmpexamples.example007

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class AppActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var selectedTabIndex by remember { mutableIntStateOf(0) }

            MaterialTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar(containerColor = Color.Transparent) {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Pets, contentDescription = null) },
                                label = { Text("Cat") },
                                selected = selectedTabIndex == 0,
                                onClick = { selectedTabIndex = 0 },
                            )

                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Pets, contentDescription = null) },
                                label = { Text("Dog") },
                                selected = selectedTabIndex == 1,
                                onClick = { selectedTabIndex = 1 },
                            )
                        }
                    },
                ) {
                    when (selectedTabIndex) {
                        0 -> PetIconComponent(PetType.CAT)
                        1 -> PetIconComponent(PetType.DOG)
                    }
                }
            }
        }
    }
}
