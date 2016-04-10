"""Computes Diophantine reciprocals"""

from fractions import gcd
import math
import sys

MAX = 2000

def main():
    """Main"""
    Diophantine.compute()
    Diophantine.brute()
    test()


#pylint:disable=invalid-name
class Diophantine(object):
    """Computes Diophantine reciprocals"""
    solutions = [0 for _ in range(MAX + 1)]
    ref_solutions = [0 for _ in range(MAX)]
    factors = [None for _ in range(MAX)]

    @staticmethod
    def compute():
        """Computes Diophantine reciprocals"""
        for a in range(2, 2 * MAX + 1):
            print a
            p = a * a
            factors = Diophantine.factorize(a)
            factors.remove(a)
            for k in factors:
                max_i = a/k
                for i in range(1, max_i/2 + 1):
                    x = a * i
                    j = max_i - i
                    if j < i:
                        break
                    y = a * j
                    if ((x * y) % (x + y) == 0):
                        n = ((x * y) / (x + y))
                        if n <= MAX:
                            # print (a, i, j, n, x, y)
                            Diophantine.solutions[n] = Diophantine.solutions[n] + 1
        '''
        for x in range(2, 2 * MAX + 1):
            # print x
            pf = Diophantine.prime_factorize(x)
            
            for y in range(x, x * (x - 1) + 1, x):
                if ((x * y) % (x + y)) == 0:
                    n = ((x * y) / (x + y))
                    # print (n, x, y)
                    Diophantine.solutions[n] = Diophantine.solutions[n] + 1
            
            pf = Diophantine.prime_factorize(x)
            y_max = x * (x - 1)
            closed = set()
            for y_delta in pf:
                for y in range(x, y_max + 1, y_delta):
                    if y in closed:
                        counts = counts + 1
                    closed.add(y)
                    if ((x * y) % (x + y)) == 0:
                        n = ((x * y) / (x + y))
                        # print (n, x, y)
                        Diophantine.solutions[n] = Diophantine.solutions[n] + 1
            '''

    @staticmethod
    def brute():
        """Brute-force search for Diophantine reciprocals"""
        for n in range(1, MAX):
            print n
            x = n + 1
            while x <= 2*n:
                y = x * n
                if y % (x - n) == 0:
                    y = y / (x - n)
                    # Check:
                    a = gcd(x, y)
                    if (a ** 2) % (x + y) != 0:
                        print "here1"
                    # print (n, x, y)
                    Diophantine.ref_solutions[n] = Diophantine.ref_solutions[n] + 1
                x = x + 1
            
    @staticmethod
    def factorize(n):
        return set(reduce(list.__add__, ([i, n//i] for i in range(1, int(n**0.5) + 1) if n % i == 0)))

    @staticmethod
    def prime_factorize(n):
        primfac = set()
        d = 2
        while d*d <= n:
            while (n % d) == 0:
                primfac.add(d)
                n //= d
            d += 1
        if n > 1:
           primfac.add(n)
        return primfac


def test():
    for index in range(MAX):
        if Diophantine.solutions[index] != Diophantine.ref_solutions[index]:
            print("Mismatch index: %d, brute: %d, compute: %d" %
                    (index, Diophantine.ref_solutions[index],
                            Diophantine.solutions[index]))


if __name__ == "__main__":
    main()


# x = n
# xmax = n * (n + 1)
# for x in range(n, xmax + 1):
#     if x not in factors
