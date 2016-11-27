#!/usr/local/bin/python3


class Stock:

    def __init__(self):
        """
        Default constructor.
        """

    @staticmethod
    def one_transaction(prices):
        """
        Only one transaction can be made, so we have to pick the highest selling
        price and the lowest buying price and also make sure the selling is after
        the buying.
        """
        max_profit = 0

        if prices is not None and len(prices) > 0:
            selling = prices[0]
            buying = prices[0]

            for price in prices:
                if selling < price:
                    selling = price
                elif buying > price:
                    buying = price
                    # We must reset the selling price since we cannot
                    # buy after selling
                    selling = 0
                max_profit = max([max_profit, selling - buying])

        return max_profit

    @staticmethod
    def unlimited_transactions(prices):
        """
        One stock can be brought and sold, and unlimited transactions can be made,
        so we have to make transactions as many as possible.

        Given:
        1, 9, 50, 6

        Then:
        T1(1, 9), T2(9, 50)
        """

        max_profit = 0

        if prices is not None and len(prices) > 1:
            selling = prices[0]
            buying = prices[0]

            for price in prices:
                if price < buying:
                    buying = price
                    # we are unable to sell stock when the buying is after
                    # the selling
                    selling = price
                elif price > selling:
                    # here, the selling definitely in the front of the buying
                    # otherwise, the previous block should be reached
                    selling = price
                    # update the max profit
                    max_profit += selling - buying
                    # once the stock has been sold, we cannot buy stock at
                    # any price before this current selling price
                    buying = selling

        return max_profit

    @staticmethod
    def two_transactions(prices):
        """
        One stock can be brought and sold.

        Given:
        1, 5, 6, 3, 7

        Then:
        T(1, 6), T(3, 7)

        1st transaction, price = 5
        max profit = 5 - 1 = 4

        1st transaction, price = 6 | 2nd transaction, price = 6
        max profit = 6 - 1 = 5     | max profit = 6 - 5 + (5 - 1) = 6

        1st transaction, price = 3 | 2nd transaction, price = 3
        max profit = 6 - 1 = 5     | max profit = 6

        1st transaction, price = 7 | 2nd transaction, price = 7
        max profit = 7 - 1 = 6     | max profit = (6 - 1) + (7 - 3) = 9

        t  -- transaction
        p  -- current price
        AP -- all prices
        MP[t][p] -- max profit in transaction t at price p

        MP[t][p] = MAX { MP[t][p - 1], MAX { AP[p] - AP[j] + MP[t - 1][j] } }, 0 <= j < p
                 = MAX { MP[t][p - 1], AP[p] + MAX { MP[t - 1][j] - AP[j] } }
        """
        # max_profit = 0

        t = 2

        if prices is not None and len(prices) > 1:
            mp = [[0 for c in range(len(prices))] for r in range(t + 1)]

            # tt = 1, tt = 2
            for tt in range(1, t + 1):

                tmp_max = mp[tt - 1][0] - prices[0]

                for j in range(1, len(prices), 1):
                    mp[tt][j] = max(mp[tt][j - 1], prices[j] + tmp_max)

                    tmp_max = max(tmp_max, mp[tt - 1][j] - prices[j])

                    # max_profit = max(max_profit, mp[tt][j])

            return mp[t][len(prices) - 1]
        else:
            return 0

    def run(self):
        input1 = None
        input2 = []
        input3 = [1]
        input4 = [1, 2]
        input5 = [2, 1]
        input6 = [7, 1, 5, 3, 6, 4]
        input7 = [1, 7, -1, -1, -1]

        inputs = [input1, input2, input3, input4, input5, input6, input7]

        # TODO: What happen if price is negative ??

        for i in inputs:
            print("One transaction, max profit for prices = {} is = {}".format(str(i), self.one_transaction(i)))

        print("\n")

        for i in inputs:
            print("Unlimited transactions, max profit for prices = {} is = {}"
                  .format(str(i), self.unlimited_transactions(i)))

        print("\n")

        for i in inputs:
            print("Two transactions, max profit for prices = {} is = {}"
                  .format(str(i), self.two_transactions(i)))
