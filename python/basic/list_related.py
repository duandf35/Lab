#!/usr/local/bin/python3


class ListRelated:

    def __init__(self):
        """
        Default constructor.
        """

    @staticmethod
    def middle_of_list(self, a_list):
        self.qs(self, a_list, 0, len(a_list) - 1)

        print("\nafter quick sort, list = {}".format(str(a_list)))

        # even
        if len(a_list) % 2 == 0:
            mid = int((a_list[int(len(a_list) / 2)] + a_list[int(len(a_list) / 2) - 1]) / 2)
        else:
            mid = a_list[int(len(a_list) / 2)]

        return mid

    @staticmethod
    def qs(self, a_list, first, last):
        if first < last:
            p = self.partition(a_list, first, last)

            # every value in the left should smaller than p
            # every value in the right should greater than p
            # no need to include p in the next sorting
            self.qs(self, a_list, first, p - 1)
            self.qs(self, a_list, p + 1, last)

    @staticmethod
    def partition(a_list, first, last):
        # get the left value of two middle values
        pivot = int((last - first) / 2) + first

        # put the pivot in the first of the section
        a_list[first], a_list[pivot] = a_list[pivot], a_list[first]

        done = False
        left = first + 1
        right = last
        while not done:
            # must be <= or >= since we still need the left, right
            # move to the next element once left == right to reach
            # the terminate condition which is right < left
            # IMPORTANT: the index of the pivot value is 'first' not 'pivot'
            while left <= right and a_list[left] <= a_list[first]:
                left = left + 1

            while left <= right and a_list[right] >= a_list[first]:
                right = right - 1

            if left > right:
                done = True
            else:
                a_list[left], a_list[right] = a_list[right], a_list[left]

        # right is the last index of the left section
        # all elements in the left should <= a_list[pivot]
        # all elements in the right should > a_list[pivot]

        # the pivot value is saved in the first index, so we have to
        # switch the value between first and right
        a_list[right], a_list[first] = a_list[first], a_list[right]

        return right

    @staticmethod
    def reverse_list(a_list):
        # slice
        new_list = a_list[::-1]

        # build-in method
        new_list.reverse()

        # naive way
        #
        # 0, 1, 2, 3, 4, 5
        #
        # terminate index = (length - 1) / 2
        #                 = (6 - 1) / 2 = 3
        # why? we need the left value of two middle values
        #
        # index 0, the opposite index = 5 - 0 = 5
        # index 1, the opposite index = 5 - 1 = 4
        # index 2, the opposite index = 5 - 2 = 3
        last_index = len(new_list) - 1

        for i in range(int((len(new_list) - 1) / 2)):
            tmp = new_list[i]
            new_list[i] = new_list[last_index - i]
            new_list[last_index - i] = tmp

        return new_list

    def run(self):
        a_list = [1, 4, 9, 5, 0, 3]

        print("reverse, original list = {}, result = {}"
              .format(str(a_list), str(self.reverse_list(a_list))))

        print("middle of list, original list = {}, result = {}"
              .format(str(a_list), str(self.middle_of_list(self, a_list))))
