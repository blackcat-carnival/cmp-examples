import ComposeApp
import SwiftUI

@main
struct ComposeApp: App {

    init() {
        HelperKt.doInitKoinType1()

//        HelperKt.doInitKoinType2 {
//            IosSwiftExampleClass()
//        }
    }

    var body: some Scene {
        WindowGroup {
            ContentView().ignoresSafeArea(.all)
        }
    }
}

struct ContentView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return MainKt.MainViewController()
    }

    func updateUIViewController(
        _ uiViewController: UIViewController,
        context: Context
    ) {
        // Updates will be handled by Compose
    }
}
