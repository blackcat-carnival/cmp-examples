package cc.bcc.cmpexamples.example005

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import bccexamples005.composeapp.generated.resources.Res
import bccexamples005.composeapp.generated.resources.mikan
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun App() {
    var mask by remember { mutableStateOf<Pair<Rect, Shape>?>(null) }
    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
        Image(
            painter = painterResource(Res.drawable.mikan),
            contentDescription = null,
            modifier =
                Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
                    .onGloballyPositioned { layoutCoordinates ->
                        val imageRect = layoutCoordinates.boundsInParent()
                        with(density) {
                            mask =
                                if (imageRect != Rect.Zero) {
                                    val fixed = imageRect.padding(16.dp.toPx())
                                    Pair(fixed, RoundedCornerShape(16.dp.toPx()))
                                } else {
                                    null
                                }
                        }
                    },
        )

        mask?.let {
            Spotlight(listOf(it))
        }
    }
}

fun Rect.padding(padding: Float): Rect =
    copy(
        left = this.left - padding,
        top = this.top - padding,
        bottom = this.bottom + padding,
        right = this.right + padding,
    )
