import UIKit

class TopBarController: UIViewController {

    let StackView = UIStackView(frame: CGRect.zero)
    let viewControllers: [UIViewController]
    var prevIndex = -1
    var curIndex = 0

    convenience init(_ viewControllers: UIViewController...) {
        self.init(viewControllers: viewControllers)
    }

    init(viewControllers: [UIViewController]) {
        self.viewControllers = viewControllers
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        super.loadView()
        
        view = UIView()
        view.insertSubview(StackView, at: viewControllers.count)
        for i in 0..<viewControllers.count {
            addChild(viewControllers[i])
            view.insertSubview(viewControllers[i].view, at: i)
            viewControllers[i].didMove(toParent: self)
            
            let element = TopBarButton()
            element.updateLabel(text: viewControllers[i].topBarItem!.title)
            element.updateIcon(image: viewControllers[i].topBarItem!.icon)
            element.updateIndex(index: i)
            StackView.addArrangedSubview(element)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        for i in 0..<StackView.subviews.count {
            let element = StackView.subviews[i] as! TopBarButton
            element.button.tag = element.index
            element.button.addTarget(self, action: #selector(buttonDidTap), for: .touchUpInside)

            view.subviews[i].isHidden = true
        }
    }
    
    @objc func buttonDidTap(button: UIButton!) {
        print(button.tag)
        prevIndex = curIndex
        curIndex = button.tag
        view.setNeedsLayout()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        
        for i in 0..<viewControllers.count {
            view.subviews[i].frame = view.bounds
        }
        if (prevIndex != -1) {
            view.subviews[prevIndex].isHidden = true
        }
        view.subviews[curIndex].isHidden = false
        print("cur = \(curIndex)")
        
        StackView.backgroundColor = .lightGray
        StackView.backgroundColor = StackView.backgroundColor?.withAlphaComponent(0.5)
        StackView.layer.cornerRadius = 15
        StackView.clipsToBounds = false
        
        StackView.frame = CGRect(x: Int(view.bounds.width) / 20, y: Int(view.bounds.height) / 8, width: Int(view.bounds.width) - Int(view.bounds.width) / 10, height: Int(view.bounds.height) / 10)
        StackView.spacing = (StackView.bounds.size.width - 55 * CGFloat(self.viewControllers.count)) / (CGFloat(self.viewControllers.count - 1)) / 2
    }
}
