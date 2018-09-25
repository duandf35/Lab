class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
        Output: 7 -> 0 -> 8

        7 % 10 = 7
        10 % 10 = 0
        11 % 10 = 1

        reminder need to be rolled over to the next turn

        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        d1 = l1.val
        d2 = l2.val
        val = d1 + d2
        offset = 0
        if (val >= 10):
            val = val % 10
            offset = 1

        result = ListNode(val)
        head = result

        while l1.next is not None or l2.next is not None or offset > 0:
            if (l1 and l1.next):
                l1 = l1.next
                d1 = l1.val
            else:
                d1 = 0

            if (l2 and l2.next):
                l2 = l2.next
                d2 = l2.val
            else:
                d2 = 0
            
            val = d1 + d2 + offset
            if (val >= 10):
                val = val % 10
                offset = 1
            else:
                offset = 0

            result.next = ListNode(val)
            result = result.next

        return head
