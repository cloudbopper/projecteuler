"""Solution to problem 104"""

import numpy as np

SQRT_FIVE = np.sqrt(5)
PHI = (1 + SQRT_FIVE)/2
PSI = (1 - SQRT_FIVE)/2
MAX = 1000000


def main():
    """Main"""
    for i in range(MAX):
        print i
        elem = Fibonacci.fibonacci(i)
        elem_str = str(elem)
        if is_pandigital(elem_str[:9]) and is_pandigital(elem_str[-9:]):
            break


def is_pandigital(string):
    """Returns true if string contains all integers from 1 - 9"""
    digits = set()
    for char in string:
        val = int(char)
        if val != 0:
            digits.add(int(char))
        if len(digits) == 9:
            return True
    return False


class Fibonacci(object):
    """Class implementing Fibonacci numbers"""
    fibonacci_list = [-1 for _ in range(MAX)]
    fibonacci_list[0] = 0
    fibonacci_list[1] = 1

    # pylint:disable=invalid-name
    @staticmethod
    def fibonacci(n):
        """Returns n'th fibonacci number"""
        assert n < MAX
        elem = Fibonacci.fibonacci_list[n]
        if elem < 0:
            elem = Fibonacci.fibonacci(n - 1) + Fibonacci.fibonacci(n - 2)
            Fibonacci.fibonacci_list[n] = elem
        return elem

    @staticmethod
    def fibonacci_closed_form(n):
        """Returns closed-form n'th fibonacci number"""
        return np.round((np.power(PHI, n) - np.power(PSI, n))/SQRT_FIVE)


if __name__ == "__main__":
    main()
