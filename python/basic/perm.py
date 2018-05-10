#!/usr/local/bin/python3


class Permutation:

    def all_perms(self, test_str, all_perms):
        self.__all_perms(test_str, all_perms, 0)

    def __all_perms(self, test_str, all_perms, idx):
        if not test_str:
            return
        if idx >= len(test_str) - 1:
            all_perms.append(test_str)
            return

        for i in range(idx + 1, len(test_str)):
            if test_str[idx] != test_str[i]:
                cp_str = list(test_str)
                tmp = cp_str[idx]
                cp_str[idx] = cp_str[i]
                cp_str[i] = tmp

                cp_str = "".join(cp_str)
                if cp_str not in all_perms:
                    self.__all_perms(cp_str, all_perms, idx + 1)

        self.__all_perms(test_str, all_perms, idx + 1)

    def all_cases(self, test_str, all_cases):
        self.__all_cases(test_str, all_cases, 0)

    def __all_cases(self, test_str, all_cases, idx):
        if not test_str:
            return
        if idx == len(test_str):
            all_cases.append(test_str)
            return

        cp_str = list(test_str)
        if cp_str[idx].isupper():
            cp_str[idx] = cp_str[idx].lower()
        else:
            cp_str[idx] = cp_str[idx].upper()
        cp_str = "".join(cp_str)

        self.__all_cases(test_str, all_cases, idx + 1)
        self.__all_cases(cp_str, all_cases, idx + 1)

    def run(self):
        test_strs = ['AAA', 'ABC', 'AABB', 'ABAB']
        for test_str in test_strs:
            all_perms = []
            self.all_perms(test_str, all_perms)
            print(f"all_perms, input: {test_str}, output: {all_perms}")

        test_str = 'abcd'
        all_cases = []
        self.all_cases(test_str, all_cases)
        print(f"all_cases, input: {test_str}, output: {all_cases}")
