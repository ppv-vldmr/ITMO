# https://ru.wikipedia.org/wiki/URL
# https://en.wikipedia.org/wiki/Uniform_Resource_Identifier
# https://tools.ietf.org/html/rfc3986#appendix-B
# https://metacpan.org/pod/URI
         
my @urls = ();
my @sites = ();
my $in = "";
while(<>) {
    $in = $in.$_;
}
while ($in =~ /<\s*a.*?\bhref\s*=\s*"(\s*([^\"]*)\s*)".*?>/i) {
    push(@urls, $1);
    $in =~ s/<\s*a.*?\bhref\s*=\s*(\"\s*([^\"]*)\s*\").*?>//i;
}
        
foreach (@urls) {
    /(?<scheme>([^\/:?#]+:)?\/\/)?(?<userinfo>(\w+(:\w+)?@))?(?<host>[^:\/?#]+)(?<port>\:\d)?([:\/?#].*)?/;
    $scheme = $+{scheme};
    $userinfo = $+{userinfo};
    $host = $+{host};
    $port = $+{port};
    if(!($scheme =~ /^\s*$/) or !($port =~ /^\s*$/)) {
        push(@sites, $host);
    }
}
       
my $prevURL = " ";
foreach (sort(@sites)) {
    if ($prevURL ne $_ && !($_ =~ /^\s*$/)) {
        print $_;
        print "\n";
    }
    $prevURL = $_;
}
