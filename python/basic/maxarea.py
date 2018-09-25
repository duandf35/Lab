class Solution:
    def maxArea(self, height):
        left, right, width, maxA = 0, len(height) - 1, len(height) - 1, 0

        for w in range(width, 0, -1):
            if height[left] < height[right]:
                maxA = max(maxA, height[left] * w)
                left += 1
            else:
                maxA = max(maxA, height[right] * w)
                right -= 1
        
        return maxA


if __name__ == '__main__':
    # test_height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
    # test_height = [5, 2, 12, 1, 5, 3, 4, 11, 9, 4]
    # test_height = [2, 3, 4, 5, 18, 17, 6]
    # test_height = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    # test_height = [2, 3, 4]
    test_height = [1, 2]
    result = Solution().maxArea(test_height)
    print(f"input: {test_height}, output: {result}")
