import UIKit

class RootControllerProvider: SceneUIProvider {
    func rootViewController() -> UIViewController {
        TopBarController(
            ContentController(title: "Flame", icon: UIImage(systemName: "flame")!, color: .systemOrange),
            ContentController(title: "Bolt", icon: UIImage(systemName: "bolt")!, color: .systemBlue),
            ContentController(title: "Ant", icon: UIImage(systemName: "ant")!, color: .systemRed),
            ContentController(title: "Hare", icon: UIImage(systemName: "hare")!, color: .systemGreen),
            ContentController(title: "Tortoise", icon: UIImage(systemName: "tortoise")!, color: .systemPink)
        )
    }
}
