#!/usr/local/bin/python3


class Stock:

    def __init__(self):
        ''''''

    @staticmethod
    def one_transaction(prices):
        max_profit = 0

        if prices is not None and len(prices) > 0:

            highest = prices[0]
            lowest = prices[0]

            for price in prices:
                if highest < price:
                    highest = price
                elif lowest > price:
                    lowest = price
                    # We must reset the highest price since we cannot
                    # buy after selling
                    highest = 0
                max_profit = max([max_profit, highest - lowest])

        return max_profit

    @staticmethod
    def unlimited_transactions(prices):
        max_profit = 0

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

        for i in inputs:
            print("Unlimited transactions, max profit for prices = {} is = {}"
                  .format(str(i), self.unlimited_transactions(i)))
