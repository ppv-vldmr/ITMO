while (<>) {
    s/\b(\w)(\w)/$2$1/g;
    print;
}
