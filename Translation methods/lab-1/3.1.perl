my @array;
my $flag = 0;
while (<>) {
    s/^\s*//;
    s/\s*$//;
    s/\s+/ /g;
    if (/^$/) {
        if ($flag == 0) {
            push @array, $_
        }
        $flag = 1;
    } else {
        $flag = 0;
        push @array, $_
    }
}
while ($#array != -1 && @array[0] =~ /^$/) {
    shift(@array);
}
while ($#array != -1 && @array[$#array] =~ /^$/) {
    pop(@array);
}
foreach my $line (@array) {
    print "$line\n";
}
