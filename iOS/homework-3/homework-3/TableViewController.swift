import UIKit

class TableViewController: UIViewController, TableViewDataSource {
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
        tabBarItem.title = "Table"
        tabBarItem.image = UIImage.init(systemName: "table.fill")
    }

    override func loadView() {
        view = UIView()
        view.backgroundColor = .secondarySystemBackground

        let tableView = TableView()
        self.tableView = tableView
        view.addSubview(tableView)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView?.dataSource = self
    }

    override func viewWillLayoutSubviews() {
        tableView?.frame = view.bounds
    }

    func numberOfRowInTableView(_ tableView: TableView) -> Int {
        return 1_000_000_000_000
    }

    func tableView(_ tableView: TableView, textForRow row: Int) -> String {
        return "Row: \(row)"
    }
}
