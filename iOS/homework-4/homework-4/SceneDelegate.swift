import UIKit

protocol SceneUIProvider {
    func rootViewController() -> UIViewController
}

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?

    let sceneUIProvider: SceneUIProvider

    init(sceneUIProvider: SceneUIProvider) {
        self.sceneUIProvider = sceneUIProvider
    }

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        let window = UIWindow(windowScene: windowScene)
        let rootViewController = sceneUIProvider.rootViewController()
        window.rootViewController = rootViewController
        window.makeKeyAndVisible()
        self.window = window
    }
}

class _SceneDelegate: SceneDelegate {
    @objc init() {
        let uiProvider = RootControllerProvider()
        super.init(sceneUIProvider: uiProvider)
    }
}
