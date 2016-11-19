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
                    selling = 0
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

    def run(self):
        input1 = None
        input2 = []
        input3 = [1]
        input4 = [1, 2]
        input5 = [2, 1]
        input6 = [7, 1, 5, 3, 6, 4]

        inputs = [input1, input2, input3, input4, input5, input6]

        for i in inputs:
            print("One transaction, max profit for prices = {} is = {}".format(str(i), self.one_transaction(i)))

        print("\n")

        for i in inputs:
            print("Unlimited transactions, max profit for prices = {} is = {}"
                  .format(str(i), self.unlimited_transactions(i)))

        print("\n")
