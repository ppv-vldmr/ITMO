import UIKit

class MainSceneViewControllerFactory:  SceneDelegateViewControllerFactory {
    func createRootViewController() -> UIViewController {
        let tabBarController = UITabBarController()
        tabBarController.viewControllers = [
            createTestableTableController(),
            createTableController(),
        ]
        return tabBarController
    }

    private func createTestableTableController() -> UIViewController {
        return TestableTableViewController()
    }

    private func createTableController() -> UIViewController {
        return TableViewController()
    }
}
