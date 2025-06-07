# Compose Multiplatform

This repository shares the knowledge and insights about Compose Multiplatform gained from [Black Cat Carnival](https://bcc.cc).

## Examples

### 001.detect_background

This example demonstrates how to detect when an iOS/Android application transitions between foreground and background states using Compose Multiplatform. It implements a platform-specific lifecycle tracker that provides this information through a common StateFlow interface, which can be observed in your Compose UI.

### 002.component_to_image

This example shows how to capture Compose UI components and convert them to bitmap images. It provides a reusable `ComponentToImageBitmap` composable that can wrap any content and return it as an `ImageBitmap`, which can then be used for various purposes like sharing, saving, or displaying screenshots within your application.

### 003.di_by_koin

This example demonstrates how to implement platform-specific dependency injection using Koin in Compose Multiplatform. It shows different approaches for injecting platform-specific implementations of common interfaces, including direct Kotlin implementation for iOS and integration with Swift code.

### 004.context

This example demonstrates two different approaches for accessing platform-specific context in Compose Multiplatform. It shows how to use `LocalPlatformContext` from Coil3 and `LocalContext` on Android to implement platform-specific functionality (URL opening) while maintaining a clean common interface across platforms.

### 005.musking

This example demonstrates cutout drawing techniques (creating transparent areas) and spotlight drawing effects in Compose Multiplatform. It shows how to implement a `Spotlight` composable that can create transparent areas with custom shapes over content, useful for creating spotlight or highlight effects in tutorials or onboarding screens.
