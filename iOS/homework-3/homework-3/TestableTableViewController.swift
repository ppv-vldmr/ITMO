import UIKit

class TestableTableViewController: UIViewController, TableViewDataSource {
    weak var tableView: TableView?

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        commonInit()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }

    private func commonInit() {
        tabBarItem.title = "Testable Table"
        tabBarItem.image = UIImage.init(systemName: "table")
    }

    override func loadView() {
        view = UIView()
        view.backgroundColor = .secondarySystemBackground

        let tableView = TableView()
        tableView.clipsToBounds = true
        tableView.layer.borderWidth = 1
        self.tableView = tableView
        view.addSubview(tableView)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView?.dataSource = self
    }

    override func viewWillLayoutSubviews() {
        tableView?.frame = view.bounds
            .inset(by: view.safeAreaInsets)
            .insetBy(dx: 50, dy: 50)
    }

    func numberOfRowInTableView(_ tableView: TableView) -> Int {
        return 50
    }

    func tableView(_ tableView: TableView, textForRow row: Int) -> String {
        return "Row: \(row)"
    }
}
