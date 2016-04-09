"""Identifies optimal set of size n"""

from itertools import chain
from itertools import combinations
import sys

MAX = sys.maxint
LIMIT = 50

def main():
    """Main"""
    test()
    print get_optimal_special_set(7, ll=range(19, 50))

# pylint:disable=invalid-name
def powerset(S):
    """Returns powerset of set"""
    ll = list(S)
    return chain.from_iterable(combinations(ll, r) for r in range(len(ll)+1))


def is_special(S):
    """Returns true if set is a special set"""
    size = len(S)
    sums = set()
    min_sum_for_size = [MAX for _ in range(size + 1)]
    max_sum_for_size = [0 for _ in range(size + 1)]
    for elem in powerset(S):
        sum_elem = sum(elem)
        size_elem = len(elem)
        if sum_elem in sums:
            return False
        sums.add(sum_elem)
        if max_sum_for_size[size_elem] < sum_elem:
            max_sum_for_size[size_elem] = sum_elem
        if min_sum_for_size[size_elem] > sum_elem:
            min_sum_for_size[size_elem] = sum_elem
    for i in range(size):
        if max_sum_for_size[i] >= min_sum_for_size[i + 1]:
            return False
    return True


def get_optimal_special_set(size, ll = range(1, LIMIT)):
    """Returns optimal specials set of given size >= 1 and <= 7"""
    assert size >= 1 and size <= 7
    for elem in combinations (ll, size):
        if is_special(elem):
            return elem

def test():
    """Test commands"""
    print is_special([1, 2])
    print is_special([2, 3, 4])
    print is_special([3, 5, 6, 7])
    print is_special([6, 9, 11, 12, 13])
    print is_special([11, 17, 20, 22, 23, 24])
    print is_special([11, 18, 19, 20, 22, 25])

    print get_optimal_special_set(1)
    print get_optimal_special_set(2)
    print get_optimal_special_set(3)
    print get_optimal_special_set(4)
    print get_optimal_special_set(6, ll=range(11, 26))

if __name__ == "__main__":
    main()
