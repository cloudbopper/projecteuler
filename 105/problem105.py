"""Identifies optimal set of size n"""

import sys
sys.path.insert(0, "../103")
from problem103 import is_special

def main():
    """Main"""
    test()
    filename = "p105_sets.txt"
    acc = 0
    with open(filename) as myfile:
        for line in myfile:
            strarr = line.split(",")
            intarr = [int(x) for x in strarr]
            if is_special(intarr):
                acc = acc + sum(intarr)
    print acc

def test():
    print is_special([81, 88, 75, 42, 87, 84, 86, 65])
    print is_special([157, 150, 164, 119, 79, 159, 161, 139, 158])

if __name__ == "__main__":
    main()
