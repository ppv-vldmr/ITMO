while (<>) {
    s/\b(\w+)(\W*)(\w+)\b/$3$2$1/;
    print;
}
