class Solution:
    def longestCommonPrefix(self, strs):
        """
        Input: ["flower","flow","flight"]
        Output: "fl"

        :type strs: List[str]
        :rtype: str
        """

        if not strs:
            return ''

        lcp = strs[0]
        for i in range(1, len(strs)):
            j = 0
            while j < len(lcp) and j < len(strs[i]) and strs[i][j] == lcp[j]:
                j += 1
            lcp = lcp[:j]

        return lcp


if __name__ == '__main__':
    # strs = ["flower", "flow", "flight"]
    strs = ["dog", "racecar", "car"]
    r = Solution().longestCommonPrefix(strs)
    print(r)
