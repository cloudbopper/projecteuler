"""Computes minimum spanning tree"""

import math
import random
import sys

def main():
    """Main"""
    filename = "p107_network.txt"
    adjacency_matrix = []
    with open(filename) as myfile:
        for line in myfile:
            line = str.replace(line, "-", "inf")
            adjacency_matrix.append([float(s) for s in line.split(",")])
    print count(adjacency_matrix)
    spanning_tree = prim(adjacency_matrix)
    print count(adjacency_matrix) - count(spanning_tree)


def prim(adjacency_matrix):
    """Computes minimum spanning using Prim's algorithm"""
    num_vertices = len(adjacency_matrix)
    spanning_tree = [[float("inf") for _ in range(num_vertices)]
                     for _ in range(num_vertices)]
    connected = set([random.randint(0, num_vertices)])
    while len(connected) != num_vertices:
        min_source = None
        min_sink = None
        min_weight = sys.maxint
        sink = None
        for source in connected:
            for sink in range(num_vertices):
                if sink in connected:
                    continue
                if adjacency_matrix[source][sink] < min_weight:
                    min_weight = adjacency_matrix[source][sink]
                    min_source = source
                    min_sink = sink
        connected.add(min_sink)
        spanning_tree[min_source][min_sink] = min_weight
        spanning_tree[min_sink][min_source] = min_weight
    return spanning_tree


def count(adjacency_matrix):
    """Counts sum of weights in adjacency matrix"""
    value = 0
    for row in adjacency_matrix:
        for elem in row:
            if not math.isinf(elem):
                value = value + elem
    return value/2


if __name__ == "__main__":
    main()
