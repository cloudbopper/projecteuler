"""Identifies optimal set of size n"""

from math import factorial as fact
import sys
sys.path.insert(0, "../103")
# pylint:disable=import-error
from problem103 import powerset

def main():
    """Main"""
    print compute(4)
    print compute(7)
    print compute(12)


# pylint:disable=invalid-name,too-many-locals
def compute(n):
    """Computes number of equality tests necessary to identify special set"""
    power = list(powerset(range(n)))
    tuples_for_size = [[] for _ in range(n + 1)]
    for elem in power:
        tuples_for_size[len(elem)].append(elem)
    count = 0
    for r in range(2, n/2 + 1):
        for lindex in range(len(tuples_for_size[r]) - 1):
            lelem = tuples_for_size[r][lindex]
            lset = set(lelem)
            lsorted = sorted(lelem)
            for rindex in range(lindex + 1, len(tuples_for_size[r])):
                relem = tuples_for_size[r][rindex]
                rset = set(relem)
                if not lset.intersection(rset):
                    rsorted = sorted(relem)
                    sign = None
                    valid = False
                    for i in range(r):
                        if not sign:
                            if lsorted[i] < rsorted[i]:
                                sign = "-"
                            else:
                                sign = "+"
                        elif sign == "-" and lsorted[i] > rsorted[i]:
                            valid = True
                            break
                        elif sign == "+" and lsorted[i] < rsorted[i]:
                            valid = True
                            break
                    if valid:
                        count = count + 1
                        # print (lelem, relem)

    return count


def nCr(n, r):
    """Combination calculator"""
    return fact(n)/(fact(r) * fact(n - r))


def test():
    """Testing function"""
    power = list(powerset(range(4)))
    power = list(powerset(range(7)))
    power.pop(0)
    pairs = set()
    for left in power:
        left = set(left)
        for right in power:
            right = set(right)
            if (len(left) > 1 and len(left) == len(right)
                    and not left.intersection(right)):
                pairs.add((str(left), str(right)))
    print pairs
    print len(pairs)


if __name__ == "__main__":
    main()
