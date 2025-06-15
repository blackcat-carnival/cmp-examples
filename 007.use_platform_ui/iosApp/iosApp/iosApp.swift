import ComposeApp
import SwiftUI

@main
struct ComposeApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView().ignoresSafeArea(.all)
        }
    }
}

struct ContentView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {

        let catViewController = MainKt.CatViewController()
        catViewController.title = "The Cat"

        let dogViewController = MainKt.DogViewController()
        dogViewController.title = "The Dog"

        // Set up the UITabBarController
        let tabBarController = UITabBarController()
        tabBarController.viewControllers = [
            // Wrap them in a UINavigationController for the titles
            UINavigationController(rootViewController: catViewController),
            UINavigationController(rootViewController: dogViewController),
        ]
        tabBarController.tabBar.items?[0].title = "Cat"
        tabBarController.tabBar.items?[0].image = UIImage(systemName: "cat")

        tabBarController.tabBar.items?[1].title = "Dog"
        tabBarController.tabBar.items?[1].image = UIImage(systemName: "dog")

        // Suspected iOS 26.0 beta bug: label rendering is incorrect until tab is switched, so forcing selection
        tabBarController.selectedIndex = 1
        tabBarController.selectedIndex = 0

        return tabBarController
    }

    func updateUIViewController(
        _ uiViewController: UIViewController,
        context: Context
    ) {
        // Updates will be handled by Compose
    }
}
