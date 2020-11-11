import UIKit

class ContentController: UIViewController {
    let contentColor: UIColor
    init(title: String, icon: UIImage, color: UIColor) {
        self.contentColor = color
        super.init(nibName: nil, bundle: nil)
        topBarItem = TopBarItem(title: title, icon: icon)
    }

    override func loadView() {
        view = UIView()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = contentColor
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

}
