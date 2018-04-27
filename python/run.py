#!/usr/local/bin/python3

from dp.stock import Stock
from basic.sorting import Sorting
from basic.bst import Bst

if __name__ == "__main__":
    s = Stock()
    lr = Sorting()
    bst = Bst()
    classes = [
        # s,
        # lr
        bst
    ]

    for clazz in classes:
        clazz.run()
