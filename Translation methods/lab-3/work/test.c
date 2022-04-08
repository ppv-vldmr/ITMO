#include "kek.h"

long long gcd1(long long a, long long b);
long long lcm1(long long a, long long b);
int main();
long long gcd1(long long a, long long b)
{
	if ((b == 0))
	{
		return a;
	}
	else
	{
		return gcd1(b, (a % b));
	}
}
long long lcm1(long long a, long long b)
{
	return ((a * b) / gcd1(a, b));
}
int main()
{
	long long x;
	long long y;
	long long tmp1184524260141971010 = readint();
	long long tmp185622420678039083 = readint();
	x = tmp1184524260141971010;
	y = tmp185622420678039083;
	long long tmp3627733744243303843 = y;
	long long tmp8652197881565592533 = x;
	x = tmp3627733744243303843;
	y = tmp8652197881565592533;
	print(x);
	println();
	print(y);
	println();
	print(lcm1(x, y));
	println();
	print(gcd1(x, y));
	println();
}
