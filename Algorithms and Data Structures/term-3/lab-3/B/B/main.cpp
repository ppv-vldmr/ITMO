#include <iostream>
#include <vector>

int main() {
    std::string s;
    std::cin >> s;
    std::vector<int> pr;
    pr.resize(s.size());
    for (int i = 1; i < s.size(); ++i) {
        int j = pr[i - 1];
        while (j > 0 && s[i] != s[j])
            j = pr[j - 1];
        if (s[i] == s[j]) ++j;
        pr[i] = j;
    }
    for(int i = 0;i<pr.size();++i){
        std::cout<<pr[i]<<' ';
    }
    return 0;
}
