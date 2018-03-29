#!/usr/bin/env python3


def cone(num):
    """
    Find out the number of '1' in the given number.
    """
    count = 0
    dividend = num
    reminder = 0
    loop = 0
    while True:
        if dividend == 0:
            break
        reminder = dividend % 10
        if reminder == 1:
            count += 1
        dividend = int(dividend / 10)
        loop += 1
        print(f"loop[{loop}] reminder = {reminder}, dividend = {dividend}, count = {count}")
    return count


if __name__ == '__main__':
    from sys import argv

    if len(argv) < 2:
        print("Number is not provided!")
    else:
        print(f"There are {cone(int(argv[1]))} digit '1' in number {argv[1]}")
