class Solution:
    def convert(self, s, numRows):
        """ 
        input: 

            PAYPALISHIRING

        zig-zag:
            P   A   H   N
            A P L S I I G
            Y   I   R

        output:
            PAHNAPLSIIGYIR

        numRows = 4

          1     4      
           _____ _____ i = i + numRows - 1
          |  ___|_ ___ i = i + numRows - 1
          | |   | |   
        c c c c z z c c c c z z c c c c ...
        | | |___| | |___ i = (i + numRows - 1) + (numRows - 2 - 1 - 2)
        | |_______| |___ i = (i + numRows - 1) + (numRows - 2 - 1)
        | 1       5 |
        |___________|___ i = i + numRows + (numRows - 2)
        0           6

        :type s: str
        :type numRows: int
        :rtype: str
        """

        if numRows < 2:
            return s

        result = ''

        i = 0

        # first row in zig-zag
        while i < len(s):
            result += s[i]
            i = i + numRows + numRows - 2

        adj = numRows - 2 - 1
        for j in range(1, numRows - 1):
            i = j
            while i < len(s):
                result += s[i]

                # calculate corresponding element in the zig-zag area
                if i + numRows - 1 + adj < len(s):
                    result += s[i + numRows - 1 + adj]
                i = i + numRows + numRows - 2
            adj -= 2

        # last row in zig-zag
        i = numRows - 1
        while i < len(s):
            result += s[i]
            i = i + numRows + numRows - 2

        return result

if __name__ == '__main__':
    result = Solution().convert('ab', 1)
    print(result)
