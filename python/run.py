#!/usr/local/bin/python3

from dp.stock import Stock

if __name__ == "__main__":
    s = Stock()
    classes = [s]

    for clazz in classes:
        clazz.run()
