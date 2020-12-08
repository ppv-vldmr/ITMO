import UIKit

class ViewController: UIViewController {
    
    @IBOutlet private var tableView: UITableView!
    
    let images = [
        "https://i.picsum.photos/id/1026/4621/3070.jpg?hmac=OJ880cIneqAKIwHbYgkRZxQcuMgFZ4IZKJasZ5c5Wcw",
        "https://i.picsum.photos/id/1027/2848/4272.jpg?hmac=EAR-f6uEqI1iZJjB6-NzoZTnmaX0oI0th3z8Y78UpKM",
        "https://i.picsum.photos/id/1028/5184/3456.jpg?hmac=WhttNfn25eTgLTNnhRujSq4IVjx2mMa6wvPG1c6qMVc",
        "https://i.picsum.photos/id/1029/4887/2759.jpg?hmac=uMSExsgG8_PWwP9he9Y0LQ4bFDLlij7voa9lU9KMXDE",
        "https://i.picsum.photos/id/1031/5446/3063.jpg?hmac=Zg0Vd3Bb7byzpvy-vv-fCffBW9EDp1coIbBFdEjeQWE",
        "https://i.picsum.photos/id/1032/2880/1800.jpg?hmac=5SLBdyPZBMyr5IBkIRfffZV10bP87Y7RrxVZX1vCefA",
        "https://i.picsum.photos/id/1033/2048/1365.jpg?hmac=zEuPfX7t6U866nzXjWF41bf-uxkKOnf1dDrHXmhcK-Q",
        "https://i.picsum.photos/id/1035/5854/3903.jpg?hmac=DV0AS2MyjW6ddofvSIU9TVjj1kewfh7J3WEOvflY8TM",
        "https://i.picsum.photos/id/1036/4608/3072.jpg?hmac=Tn9CS_V7lFSMMgAI5k1M38Mdj-YEJR9dPJCyeXNpnZc",
        "https://i.picsum.photos/id/1037/5760/3840.jpg?hmac=fZe213BcO2KPQEJKChsdHnVYg-6kAtQMTZV24f1fS94",
        "https://i.picsum.photos/id/1038/3914/5863.jpg?hmac=SGtXryWDkn_eVQdA1NjYrikcsrIfcfS4SFYHo4lCpkQ",
        "https://i.picsum.photos/id/1039/6945/4635.jpg?hmac=tdgHDygif2JPCTFMM7KcuOAbwEU11aucKJ8eWcGD9_Q",
        "https://i.picsum.photos/id/104/3840/2160.jpg?hmac=Rv0qxBiYb65Htow4mdeDlyT5kLM23Z2cDlN53YYldZU",
        "https://i.picsum.photos/id/1040/4496/3000.jpg?hmac=kvZONlBpTcZ16PuE_g2RWxlicQ5JKVq2lqqZndfafBY",
        "https://i.picsum.photos/id/1041/5184/2916.jpg?hmac=TW_9o6HeD7H7I7NVo-S1Fa1iAvzQ10uvmJqsXvNoi0M",
        "https://i.picsum.photos/id/1042/3456/5184.jpg?hmac=5xr8Veg2D_kEQQO6rvGj_Yk8s_N4iq3-eZ9_KclSXNQ",
        "https://i.picsum.photos/id/1043/5184/3456.jpg?hmac=wsz2e0aFKEI0ij7mauIr2nFz2pzC8xNlgDHWHYi9qbc",
        "https://i.picsum.photos/id/1044/4032/2268.jpg?hmac=BXmoMkaurlzpTLYQupXLipcmI1sFbgT5sIz98Ob5VZE",
        "https://i.picsum.photos/id/1045/3936/2624.jpg?hmac=PMfAbC94Asle_jgeRYsj7zQHFabfTfsIwL247Ewetwc",
        "https://i.picsum.photos/id/1047/3264/2448.jpg?hmac=ksy0K4uGgm79hAV7-KvsfHY2ZuPA0Oq1Kii9hqkOCfU",
    ]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
    }
    
}


extension ViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return images.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "imageCell", for: indexPath) as! ImageCell
        cell.update(imageUrl: images[indexPath.row])
        return cell
    }
    
}
