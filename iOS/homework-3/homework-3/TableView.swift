import UIKit
 
protocol TableViewDataSource: AnyObject {
    func numberOfRowInTableView(_ tableView: TableView) -> Int
    func tableView(_ tableView: TableView, textForRow row: Int) -> String
}
 
class TableView: UIScrollView {
    weak var dataSource: TableViewDataSource?
    
    var cellArray: [TableCell] = []
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    private func commonInit() {
        backgroundColor = .secondarySystemBackground
        
        cellArray.append(TableCell())
        addSubview(cellArray[0])
    }
    
    
    
    override func layoutSubviews() {
        let cellWidth = self.bounds.width
        let cellHeight: Int = 40
        let numberOfCells = Int(bounds.height / CGFloat(cellHeight)) + 1
        
        super.layoutSubviews()
        contentSize = CGSize(width: Int(bounds.width), height: cellHeight * (dataSource?.numberOfRowInTableView(self) ?? 0))
        
        if (cellArray.count < numberOfCells) {
            for _ in 1...numberOfCells {
                cellArray.append(TableCell())
                addSubview(cellArray.last!)
            }
        }
        
        let offset = Int(bounds.origin.y / CGFloat(cellHeight))
        let from = 1 + offset
        let to = numberOfCells + offset
        if (from > 0 && to <= (dataSource?.numberOfRowInTableView(self) ?? 0)) {
            for i in from...to  {
                
                let index = i - 1 - offset
                cellArray[index].frame = CGRect(x: 0, y: cellHeight * (i - 1), width: Int(cellWidth), height: cellHeight)
                cellArray[index].update(text: (dataSource?.tableView(self, textForRow: i)) ?? "#MISSED TEXT#")
            }
        }
    }
}
