#!/usr/local/bin/python3

from dp.stock import Stock
from basic.list_related import ListRelated

if __name__ == "__main__":
    s = Stock()
    lr = ListRelated()
    classes = [
        # s,
        lr]

    for clazz in classes:
        clazz.run()
