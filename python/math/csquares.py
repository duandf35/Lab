#!/usr/bin/env python3


def csquares(num1, num2):
    ps = []

    # TODO: what's the best way to handle the input?
    if num1 < 0 and num2 < 0:
        return ps

    num1 = num1 if num1 >= 0 else 0
    num2 = num2 if num2 >= 0 else 0

    lb = sqroot(num1)
    ub = sqroot(num2)

    if lb > ub:
        lb, ub = ub, lb

    for n in range(int(lb), int(ub) + 1):
        ps.append(n * n)

    return ps


def sqroot(num):
    lb = 0
    ub = num
    root = num / 2
    delta = .00000001
    while True:
        sqr = root * root
        if (sqr - num) > delta:
            ub = root
        elif (num - sqr) > delta:
            lb = root
        else:
            break
        root = (ub + lb) / 2
    return int(root)


if __name__ == '__main__':
    from sys import argv

    if len(argv) < 3:
        print("Need provide two numbers!")
    else:
        print(f"The perfect sequares are {csquares(int(argv[1]), int(argv[2]))} between {argv[1]} and {argv[2]}")
