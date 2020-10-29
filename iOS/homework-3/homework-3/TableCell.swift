import UIKit

class TableCell: UIView {
    private let label = UILabel()

    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }

    private func commonInit() {
        backgroundColor = .systemBackground
        addSubview(label)
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        layer.borderWidth = 0.5
        label.sizeToFit()
        label.layer.anchorPoint = CGPoint(x: 0, y: 0.5)
        label.center = CGPoint(x: directionalLayoutMargins.leading, y: bounds.midY)
        label.frame = label.frame.integral
    }

    func update(text: String) {
        label.text = text
        setNeedsLayout()
    }
}
