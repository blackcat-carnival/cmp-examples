package cc.bcc.cmpexamples.example001

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cc.bcc.cmpexamples.example001.lifecycle.LocalAppLifecycleTracker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackCheck(modifier: Modifier = Modifier) {
    val tracker = LocalAppLifecycleTracker.current
    val logs = mutableStateListOf<String>()

    LaunchedEffect(Unit) {
        tracker.isInForeground.collect { isInForeground ->
            val entry = "App is in foreground: $isInForeground"
            println(entry)
            logs.add(entry)
        }
    }

//    // This approach doesn't work on iOS with Compose Multiplatform.
//    val isInForeground by tracker.isInForeground.collectAsState()
//    LaunchedEffect(isInForeground) {
//        val entry = "App is in foreground: $isInForeground"
//        println(entry)
//        logs.add(entry)
//    }

    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("App Lifecycle Tracker Example") },
                )
            },
            modifier = modifier,
        ) { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                logs.forEach { log ->
                    Text(text = log, modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}
