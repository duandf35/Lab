#!/usr/bin/env python3


def convert(num, mod):
    import re

    if mod == 't' and re.search(r'^[0-1]+$', num):
        return __to_10(num)
    elif mod == 'b' and re.search(r'^[0-9]+$', num):
        return __to_2(num)
    else:
        print(f"Invalid number format: {num} for mod: {mod}")


def __to_10(num):
    result = 0
    e = 0
    for i in range(len(num) - 1, -1, -1):
        result += int(num[i]) * (2 ** e)
        e += 1
    return result


def __to_2(num):
    result = []
    e = int(num)
    while e > 1:
        result.append(str(e % 2))
        e = int(e / 2)
    result.append(e)
    result.reverse()
    return ''.join(str(x) for x in result)


if __name__ == '__main__':
    from sys import argv

    if len(argv) < 3:
        print("Number and mod must be provided!")
    else:
        print(f"Convert {argv[1]} to {convert(argv[1], argv[2])}")
