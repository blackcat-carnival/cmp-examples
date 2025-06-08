package cc.bcc.cmpexamples.example006

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bccexamples006.composeapp.generated.resources.Res
import bccexamples006.composeapp.generated.resources.bcc
import bccexamples006.composeapp.generated.resources.mikan
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun App() {
    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("ImageBitmap⇄Bitmap/UIImage") },
                )
            },
        ) { innerPadding ->

            val bccResource = imageResource(Res.drawable.bcc)
            val bcc =
                remember(bccResource) {
                    val platformBitmap = bccResource.toPlatformBitmap()
                    platformBitmap.toImageBitmap()
                }

            val mikanResource = imageResource(Res.drawable.mikan)
            val mikan =
                remember(mikanResource) {
                    val platformBitmap = mikanResource.toPlatformBitmap()
                    platformBitmap.toImageBitmap()
                }

            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(100.dp).padding(16.dp),
                        painter = painterResource(Res.drawable.bcc),
                        contentDescription = "bcc",
                    )

                    Text("→")

                    Image(
                        modifier = Modifier.size(100.dp).padding(16.dp),
                        bitmap = bcc,
                        contentDescription = "bcc",
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(100.dp).padding(16.dp),
                        painter = painterResource(Res.drawable.mikan),
                        contentDescription = "mikan",
                    )

                    Text("→")

                    Image(
                        modifier = Modifier.size(100.dp).padding(16.dp),
                        bitmap = mikan,
                        contentDescription = "mikan",
                    )
                }
            }
        }
    }
}
