package cc.bcc.cmpexamples.example002

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun App() {
    var capturedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    var shouldCapture by remember { mutableStateOf(false) }

    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Capture Example") },
                )
            },
        ) { innerPadding ->

            Column(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // Sample component to capture
                Box {
                    if (shouldCapture) {
                        capturedImage =
                            ComponentToImageBitmap(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                SampleComponent()
                            }
                    }

                    SampleComponent()
                }

                // Capture button
                Button(
                    onClick = {
                        shouldCapture = !shouldCapture
                        if (!shouldCapture) capturedImage = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(if (shouldCapture) "Reset" else "Capture Component")
                }

                // Display captured image
                capturedImage?.let { bitmap ->
                    Image(
                        bitmap = bitmap,
                        contentDescription = "Captured component",
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .border(2.dp, Color.Gray),
                    )
                }
            }
        }
    }
}

@Composable
private fun SampleComponent() {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF6200EE)),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Hello Capture!",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "This component will be captured",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
            )
        }
    }
}
