#!/usr/local/bin/python3

from dp.stock import Stock
from basic.sorting import Sorting
from basic.bst import Bst
from basic.ll import LL
from basic.perm import Permutation

if __name__ == "__main__":
    s = Stock()
    lr = Sorting()
    bst = Bst()
    ll = LL()
    perm = Permutation()
    classes = [
        # s,
        # lr,
        # bst,
        # ll,
        perm
    ]

    for clazz in classes:
        clazz.run()
