#ifndef KEK
#define KEK

#include <stdio.h>
#include <stdlib.h>

long long readint() {
    long long x;
    scanf("%lld", &x);
    return x;
}

bool readbool() {
    long long x = readint();
    return x != 0;
}

bool print(long long x) {
    printf("%lld", x);
    return true;
}

bool printspace() {
    printf(" ");
    return true;
}


bool println() {
    printf("\n");
    return true;
}

#define kek_get_array(arr, i) arr[i]

#endif