class Solution:
    def isPalindrome(self, x):
        """
        How to handle trailing zero?

        :type x: int
        :rtype: bool
        """

        y = x
        # any number with trailing zero is impossible to be palindrome
        if y > 0 and y % 10 == 0:
            return False

        r = 0
        
        while True:
            d = y % 10

            r = r * 10 + d

            print(f"r = {r}, y = {y}, y / 10 = {int(y / 10)}")
            if r == y or r == int(y / 10):
                return True

            y = int(y / 10)

            if y < 10:
                break

        return False


if __name__ == '__main__':
    from sys import argv
    num = 1221
    if len(argv) > 1:
        num = int(argv[1])
    result = Solution().isPalindrome(num)
    print(f"number = {num}, result = {result}")
