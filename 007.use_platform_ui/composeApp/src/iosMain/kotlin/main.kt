@file:Suppress("unused", "FunctionName")

import androidx.compose.ui.window.ComposeUIViewController
import cc.bcc.cmpexamples.example007.PetIconComponent
import cc.bcc.cmpexamples.example007.PetType
import platform.UIKit.UIViewController

fun CatViewController(): UIViewController =
    ComposeUIViewController { PetIconComponent(PetType.CAT) }

fun DogViewController(): UIViewController =
    ComposeUIViewController { PetIconComponent(PetType.DOG) }
