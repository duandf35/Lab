#!/usr/local/bin/python3

from dp.stock import Stock
from basic.sorting import Sorting
from basic.bst import Bst
from basic.ll import LL

if __name__ == "__main__":
    s = Stock()
    lr = Sorting()
    bst = Bst()
    ll = LL()
    classes = [
        # s,
        # lr,
        bst,
        # ll
    ]

    for clazz in classes:
        clazz.run()
