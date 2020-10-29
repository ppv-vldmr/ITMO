import UIKit

protocol SceneDelegateViewControllerFactory {
    func createRootViewController() -> UIViewController
}

class SceneDelegate<ControllerFactory: SceneDelegateViewControllerFactory>: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
    let viewControllerFactory: ControllerFactory

    init(viewControllerFactory: ControllerFactory) {
        self.viewControllerFactory = viewControllerFactory
        super.init()
    }

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        window = (scene as? UIWindowScene).map(UIWindow.init(windowScene:))
        window?.rootViewController = viewControllerFactory.createRootViewController()
        window?.makeKeyAndVisible()
    }
}

class _SceneDelegate: SceneDelegate<MainSceneViewControllerFactory> {
    @objc init() {
        let viewControllerFactory = MainSceneViewControllerFactory()
        super.init(viewControllerFactory: viewControllerFactory)
    }
}
