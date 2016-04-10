"""Computes Diophantine reciprocals"""

from fractions import gcd
import sys

MAX = 30

def main():
    """Main"""
    Diophantine.brute()
    # Diophantine.compute()
    for index, elem in enumerate(Diophantine.solutions):
        print index, elem
        if (elem > 1000):
            break

#pylint:disable=invalid-name
class Diophantine(object):
    """Computes Diophantine reciprocals"""
    solutions = [0 for _ in range(MAX)]
    factors = [None for _ in range(MAX)]

    @staticmethod
    def compute():
        """Computes Diophantine reciprocals"""
        for n in range(1, MAX):
            closed = set([n])
            Diophantine.factors[n] = Diophantine.factorize(n)
            Diophantine.solutions[n] = len(Diophantine.factors[n])
            continue
            for p in Diophantine.factors[n]:
                if p not in closed:
                    closed = closed.union(Diophantine.factors[p])
                    Diophantine.solutions[n] = Diophantine.solutions[n] + Diophantine.solutions[p]
            Diophantine.solutions[n] = Diophantine.solutions[n] + 1 # x = n + 1, y = n * (n + 1)


    @staticmethod
    def brute():
        """Brute-force search for Diophantine reciprocals"""
        for n in range(1, MAX):
            x = n + 1
            while x <= 2*n:
                y = x * n
                if y % (x - n) == 0:
                    y = y / (x - n)
                    # print (n, x, y)
                    Diophantine.solutions[n] = Diophantine.solutions[n] + 1
                x = x + 1
            
    @staticmethod
    def factorize(n):
        return set(reduce(list.__add__, ([i, n//i] for i in range(1, int(n**0.5) + 1) if n % i == 0)))


if __name__ == "__main__":
    main()


# x = n
# xmax = n * (n + 1)
# for x in range(n, xmax + 1):
#     if x not in factors
