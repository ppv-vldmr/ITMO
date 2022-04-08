while (<>) {
    print if /(\W|^)cat(\W|$)/
}
