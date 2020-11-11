//
//  TopBarButton.swift
//  TabBarController
//
//  Created by Владимир on 27.10.2020.
//

import UIKit
 
class TopBarButton: UIView {
    let label = UILabel()
    let button = UIButton()
    let icon = UIImageView()
    var color = UIColor()
    var index: Int = 0
 
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
 
    required init(coder: NSCoder) {
        super.init(coder: coder)!
        commonInit()
    }
 
    private func commonInit() {
        addSubview(button)
        addSubview(label)
        addSubview(icon)
        label.font = label.font.withSize(13)
        label.textAlignment = .center
    }
 
    override func layoutSubviews() {
        super.layoutSubviews()
        icon.frame = CGRect(x: self.bounds.minX, y: self.bounds.minY, width: self.bounds.maxX, height: self.bounds.maxY / 3 * 2)
        label.frame = CGRect(x: self.bounds.minX, y: self.bounds.maxY / 3 * 2, width: self.bounds.maxX, height: self.bounds.maxY / 3)
        button.frame = self.bounds
        widthAnchor.constraint(equalToConstant: 50).isActive = true
        heightAnchor.constraint(equalToConstant: 50).isActive = true
    }
    
    public func updateColor(color: UIColor) {
        self.color = color
    }
 
    public func updateLabel(text: String) {
        label.text = text
        setNeedsLayout()
    }
 
    public func updateIcon(image: UIImage) {
        icon.image = image
        setNeedsLayout()
    }
 
    public func updateIndex(index: Int) {
        self.index = index
    }
}
