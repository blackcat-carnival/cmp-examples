package cc.bcc.cmpexamples.example005

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Spotlight(
    spots: List<Pair<Rect, Shape>>,
    backgroundColor: Color = remember { Color.Black.copy(0.5f) },
) {
    val density = LocalDensity.current
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .drawBehind {
                    drawRect(backgroundColor, size = size)

                    spots.forEach { (rect, shape) ->
                        translate(rect.left, rect.top) {
                            drawOutline(
                                outline =
                                    shape.createOutline(
                                        Size(rect.width, rect.height),
                                        layoutDirection,
                                        density,
                                    ),
                                color = Color.White,
                                blendMode = BlendMode.DstOut,
                            )
                        }
                    }
                },
    )
}
