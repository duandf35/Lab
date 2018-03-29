#!/usr/bin/env python3

import re


def convert(expr):
    """
    12 -> 24 or 24 -> 12
    - detecting the format 12 or 24
    - converting the format
    - 24 hours: has no 24:00 but 0:00
    - 12 hours: ends am or pm
    """
    if is_12(expr):
        print(f"{expr} is 12 hours format")
        return __convert(expr)
    elif is_24(expr):
        print(f"{expr} is 24 hours format")
        return __convert(expr)
    else:
        print(f"{expr} is invalid format")
        return expr


def is_12(expr):
    # 12:00 pm, 01:00 am
    m = re.search(r'^(0[1-9]|1[0-2]):[0-5][0-9]\s((am)|(pm))$', expr)
    return m and m.group(0) == expr


def is_24(expr):
    # 23:00, 00:00, 01:00
    m = re.search(r'^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$', expr)
    return m and m.group(0) == expr


def __convert(expr):
    components = re.split(r'[\s:]', expr)

    hours = components[0]
    mins = components[1]
    clock = None if len(components) < 3 else components[2]

    if clock:  # 12 hours
        if hours != '12':
            divisor = 12 if clock == 'am' else 24
            rhours = (int(hours) + 12) % divisor
            rhours = '0' + str(rhours) if rhours < 10 else str(rhours)
        else:
            rhours = '00' if clock == 'am' else '12'
        return f"{rhours}:{mins}"
    else:  # 24 hours
        if hours != '12' and hours != '00':
            rhours = int(hours) % 12
            rhours = '0' + str(rhours) if rhours < 10 else str(rhours)
        else:
            rhours = '12'
        clock = 'pm' if int(hours) >= 12 else 'am'
        return f"{rhours}:{mins} {clock}"


if __name__ == '__main__':
    from sys import argv

    if len(argv) > 1:
        time_expr = argv[1]
        converted_expr = convert(time_expr)
        print(f"Convert time expression: {time_expr} to {converted_expr}")
    else:
        print('Time expression is not provided!')
