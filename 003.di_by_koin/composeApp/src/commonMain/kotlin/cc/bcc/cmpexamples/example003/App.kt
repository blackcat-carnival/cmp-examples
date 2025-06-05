package cc.bcc.cmpexamples.example003

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun App() {
    val logs = mutableStateListOf<String>()

    val koin = getKoin()

    LaunchedEffect(Unit) {
        val common = koin.get<CommonExampleClass>()
        val platform = koin.get<PlatformExampleClass>()
        logs.add(common.hello())
        logs.add(platform.hello())
    }

    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Koin Example") },
                )
            },
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
