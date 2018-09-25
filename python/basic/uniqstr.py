class Solution:
    def lengthOfLongestSubstring(self, s):
        """
        Given a string, find the length of the longest substring without repeating characters.

        :type s: str
        :rtype: int
        """
        charPos = dict()
        result = 0
        right = 0
        for left in range(0, len(s)):
            if s[left] in charPos:
                right = max(right, charPos[s[left]] + 1)
            charPos[s[left]] = left
            result = max(result, left - right + 1)

        return result
