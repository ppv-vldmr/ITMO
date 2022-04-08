while (<>) {
    print if /(\W|^)\d+(\W|$)/
}
