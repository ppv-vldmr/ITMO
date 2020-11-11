import UIKit

struct TopBarItem {
    let title: String
    let icon: UIImage
}

extension UIViewController {
    static var topBarItemKey = 0
    var topBarItem: TopBarItem? {
        get { objc_getAssociatedObject(self, &Self.topBarItemKey) as? TopBarItem }
        set { objc_setAssociatedObject(self, &Self.topBarItemKey, newValue, .OBJC_ASSOCIATION_RETAIN_NONATOMIC) }
    }
}
