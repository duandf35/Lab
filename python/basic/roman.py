class Solution:
    def intToRoman(self, num):
        """
        Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000

        I can be placed before V (5) and X (10) to make 4 and 9.
        X can be placed before L (50) and C (100) to make 40 and 90.
        C can be placed before D (500) and M (1000) to make 400 and 900.

        Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

        :type num: int
        :rtype: str
        """

        mapping = [
            (1, 'I'),
            (4, 'IV'),
            (5, 'V'),
            (9, 'IX'),
            (10, 'X'),
            (40, 'XL'),
            (50, 'L'),
            (90, 'XC'),
            (100, 'C'),
            (400, 'CD'),
            (500, 'D'),
            (900, 'CM'),
            (1000, 'M')
        ]

        mapping.reverse()

        result = ''

        val = num
        for t in mapping:
            v, s = t
            while val >= v:
                val -= v
                result += s

        return result

    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """

        mapping = {
            'I': 1,
            'IV': 4,
            'V': 5,
            'IX': 9,
            'X': 10,
            'XL': 40,
            'L': 50,
            'XC': 90,
            'C': 100,
            'CD': 400,
            'D': 500,
            'CM': 900,
            'M': 1000
        }

        result = 0

        i = 0
        while i < len(s):
            if i + 1 < len(s) and s[i] + s[i + 1] in mapping:
                result += mapping[s[i] + s[i + 1]]
                i += 2
            else:
                result += mapping[s[i]]
                i += 1

        return result


if __name__ == '__main__':
    # test_input = 1
    test_input = 123
    # test_input = 3849
    # test_input = 3999
    # test_input = 376
    # test_input = 949
    r = Solution().intToRoman(test_input)
    print(r)

    test_input2 = 'MCMXCIV'

    r2 = Solution().romanToInt(test_input2)
    print(r2)
