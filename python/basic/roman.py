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
            (1000, 'M')]

        mapping.reverse()

        result = ''

        val = num
        for t in mapping:
            v, s = t
            while val >= v:
                val -= v
                result += s
        
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
