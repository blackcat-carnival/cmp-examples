package cc.bcc.cmpexamples.example002

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.draw
import kotlinx.coroutines.delay

@Composable
fun ComponentToImageBitmap(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
): ImageBitmap? {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    // Wait until drawing is complete. Can be removed if content doesn't contain LaunchedEffect etc.
    var waited by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(500)
        waited = true
    }

    Column(
        modifier =
            modifier
                .drawWithCache {
                    val width = this.size.width.toInt()
                    val height = this.size.height.toInt()

                    val newImageBitmap = ImageBitmap(width, height)
                    val canvas = Canvas(newImageBitmap)

                    if (waited) {
                        onDrawWithContent {
                            imageBitmap = newImageBitmap
                            draw(this, this.layoutDirection, canvas, this.size) {
                                this@onDrawWithContent.drawContent()
                            }
                        }
                    } else {
                        onDrawWithContent {
                            // Wait for content to be ready
                        }
                    }
                },
    ) {
        content()
    }

    return imageBitmap
}
