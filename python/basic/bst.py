class Bst:
    class Node:
        def __init__(self, value=None, left=None, right=None):
            self.value = value
            self.left = left
            self.right = right

    def __init__(self):
        self.root = Bst.Node()

    def add(self, value):
        self.__add(self.root, value)

    def __add(self, node, value):
        if not node.value:
            node.value = value
            return

        if value < node.value:
            if not node.left:
                node.left = Bst.Node(value)
                return
            child = node.left
        else:
            if not node.right:
                node.right = Bst.Node(value)
                return
            child = node.right

        self.__add(child, value)

    def most_unique(self, node, parent, count, min_count, result):
        """
        Note:
            - set initial value of min_count
            - set initial count of root
            - not break the reference of result list
            - return count and min_count to upper recursion
        """
        if not node:
            return [count, min_count]

        count, min_count = self.most_unique(node.left, parent, count, min_count, result)

        if not parent or parent.value == node.value:
            count += 1

        if (not node.right) or (node.value != node.right.value):
            if count < min_count or min_count == 0:
                min_count = count
                # not break the ref
                result.clear()
                result.append(node)
            elif count == min_count:
                result.append(node)
            count = 1

        parent = node

        return self.most_unique(node.right, parent, count, min_count, result)

    def in_order(self, node, all_path):
        if not node:
            return

        self.in_order(node.left, all_path)

        all_path.append(node)

        self.in_order(node.right, all_path)

    def pre_order(self):
        pass

    def post_order(self):
        pass

    def run(self):
        data1 = [9, 5, 13, 1, 8, 7, 10, 21, 18, 20]
        for i in data1:
            self.add(i)
        all_path = []
        self.in_order(self.root, all_path)
        print(f"in_order, input: {data1}, output: {', '.join([str(p.value) for p in all_path])}\n")

        data2 = [1, 1, 1, 1, 1, 1]
        data3 = [1]
        data4 = [1, 1, 2, 2]
        data5 = [1, 2, 3, 4, 5, 6, 7]
        data6 = [1, 1, 2, 2, 3, 4, 4, 4, 5, 5, 6, 6, 7, 7]
        data7 = [9, 5, 13, 1, 8, 7, 10, 21, 18, 20, 9]
        for data in [data2, data3, data4, data5, data6, data7]:
            self.root = Bst.Node()
            for i in data:
                self.add(i)
            most_unique = []
            self.most_unique(self.root, None, 0, 0, most_unique)
            print(f"most_unique, input: {data}, output: {', '.join([str(p.value) for p in most_unique])}\n")
