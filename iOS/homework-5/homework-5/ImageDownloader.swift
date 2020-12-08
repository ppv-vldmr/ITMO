import UIKit
import Combine

extension URLSessionDataTask: Cancellable {}

final class ImageDownloader: Cancellable {
    
    enum CustomError: Error, CustomStringConvertible {
        case InvalidURL(annotation: String)

        var description: String {
            switch self {
            case .InvalidURL(annotation: let annotation):
                return "CustomError:: I can not find such an image \"\(annotation)\""
            }
        }
    }
    
    typealias ImageCompletion = (UIImage) -> Void
    
    static let sharedInstance = ImageDownloader()
    
    // MARK: - New Variable
    private let imageCache = NSCache<NSString, UIImage>()
    private let cacheDirectory =  FileManager.default.urls(for: .cachesDirectory, in: .userDomainMask).first
    private var mapNamesImages = Dictionary<String, String>()
    private var operation: Operation
    
    private init() {
        self.operation = BlockOperation()
    }
    
    // MARK: - Public
    func image(by urlString: String, completion: @escaping ImageCompletion) -> Cancellable {
        do {
            operation.start()
            let imageName = String(try urlString.split(separator: "/").last.unwrap(or: CustomError.InvalidURL(annotation: "BadName Image")))
            
            print("Download Started")
            if let cachedImage = imageCache.object(forKey: urlString as NSString) {
                print("Donwload from cache")
                DispatchQueue.main.async {
                    completion(cachedImage)
                }
                return ImageDownloader.sharedInstance
            } else if checkMemory(imageName: imageName) {
                print("Donwload from memory")
                DispatchQueue.main.async {
                    let memoryImage = self.loadImageFromMemory(imageName: imageName)
                    completion(memoryImage)
                    self.imageCache.setObject(memoryImage, forKey: urlString as NSString)
                }
                return ImageDownloader.sharedInstance
            } else {
                print("Donwload from Network")
                guard let url = URL(string: urlString) else {
                    print(CustomError.InvalidURL(annotation: urlString))
                    return URLSession.shared.dataTask(with: URL(string: "")!)
                }
                
                let task = URLSession.shared.dataTask(with: url) { (data, response, error) in
                    guard error == nil else {return}
                    guard let data = data else {return}
                    
                    DispatchQueue.main.async {
                        if let image = UIImage(data: data) {
                            completion(image)
                            self.saveImageInMemory(image: image, imageName: String(imageName))
                            self.imageCache.setObject(image, forKey: urlString as NSString)
                        }
                    }
                }
                task.resume()
                return task
            }
        } catch {
            print(error)
        }
        return URLSession.shared.dataTask(with: URL(string: "")!)
    }

    func cancel() {
        if !operation.isCancelled {
            operation.cancel()
        }
    }
    
    // MARK: - Private
    func checkMemory(imageName: String) -> Bool {
        
        let url = cacheDirectory
        if let pathComponent = url?.appendingPathComponent("MyCache/\(imageName)") {
            let filePath = pathComponent.path
            print(filePath)
            if FileManager.default.fileExists(atPath: filePath) {
                print("FILE AVAILABLE")
                return true
            } else {
                print("FILE NOT AVAILABLE")
                return false
            }
        } else {
            print("FILE PATH NOT AVAILABLE")
            return false
        }
    }
    
    func saveImageInMemory(image: UIImage, imageName: String) {
        do {
            let uploadURL = try (URL.createFolder(folderName: "MyCache").unwrap(or: CustomError.InvalidURL(annotation: "saveImageInMemory Invalid URL")).appendingPathComponent(imageName))
            if !FileManager.default.fileExists(atPath: uploadURL.path) {
                print("This imageName is NOT used --> Create new File")
                let data = image.jpegData(compressionQuality: 1)
                do {
                    print("Save image")
                    try data?.write(to: uploadURL)
                } catch {
                    print("Error Writing Image: \(error)")
                }
            }
            else {
                print("This imageName is used --> What!?")
            
             }
        } catch {
            print(error)
        }
        
    }
    
    func loadImageFromMemory(imageName : String) -> UIImage {
        let nsDocumentDirectory = FileManager.SearchPathDirectory.cachesDirectory
        let nsUserDomainMask = FileManager.SearchPathDomainMask.userDomainMask
        let paths = NSSearchPathForDirectoriesInDomains(nsDocumentDirectory, nsUserDomainMask, true)
        if let dirPath = paths.first {
            let imageURL = URL(fileURLWithPath: dirPath).appendingPathComponent("MyCache/\(imageName)")
            guard let image = UIImage(contentsOfFile: imageURL.path) else {
                return  UIImage.init(named: "imageDefaultPlaceholder")!
            }
            return image
        }
        return UIImage.init(named: "imageDefaultPlaceholder")!
    }
}

extension URL {
    static func createFolder(folderName: String) -> URL? {
        if let documentDirectory = FileManager.default.urls(for: .cachesDirectory, in: .userDomainMask).first {
            let folderURL = documentDirectory.appendingPathComponent(folderName)
            if !FileManager.default.fileExists(atPath: folderURL.path) {
                do {
                    try FileManager.default.createDirectory(atPath: folderURL.path, withIntermediateDirectories: true, attributes: nil)
                } catch {
                    print(error.localizedDescription)
                    return nil
                }
            }
            return folderURL
        }
        return nil
    }
}

extension Dictionary {
    func customContains(key: String, value: String) -> Bool {
    let contains = self.contains { (k, v) -> Bool in

        if let v = v as? String {
            if let k = k as? String, v == value && k != key {
                return true
            }
        }
      return false
    }
    return contains
  }
}

extension Optional {
    func unwrap(or error: Error) throws -> Wrapped {
        guard let wrapped = self else {
            throw error
        }
        return wrapped
    }
}
