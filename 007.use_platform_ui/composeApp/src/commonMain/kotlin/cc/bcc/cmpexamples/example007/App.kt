package cc.bcc.cmpexamples.example007

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class PetType(
    val emoji: String,
    val bgGradient: List<Color>,
) {
    CAT(
        "🐱",
        listOf(
            Color(0xFFFFC107),
            Color(0xFFFF9800),
        ),
    ),
    DOG(
        "🐶",
        listOf(
            Color(0xFF4CAF50), // 明るい緑
            Color(0xFF2E7D32), // 濃い緑
        ),
    ),
}

@Composable
fun PetIconComponent(
    pet: PetType,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier.fillMaxSize().background(
                brush =
                    Brush.verticalGradient(
                        colors = pet.bgGradient,
                    ),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier.size(100.dp).background(Color.White, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = pet.emoji,
                fontSize = 64.sp,
            )
        }
    }
}
