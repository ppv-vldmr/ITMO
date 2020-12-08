import UIKit
import Combine

class ImageCell: UITableViewCell {
    
    private let imageDownloader = ImageDownloader.sharedInstance
    
    private var imageDownloadOperation: Cancellable?

    func update(imageUrl: String) {
        self.textLabel?.text = imageUrl
        
        imageDownloadOperation?.cancel()
        imageDownloadOperation = imageDownloader.image(by: imageUrl) { [weak self] image in
            self?.imageView?.image = image
            self?.layoutSubviews()
        }
    }

}
