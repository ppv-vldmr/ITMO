while (<>) {
    s/\(.*?\)/()/g;
    print;
}
