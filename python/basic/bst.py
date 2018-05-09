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

    def add_all(self, values):
        for v in values:
            self.add(v)

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

    def smallest(self, node, order, q):
        if not node or len(q) == order:
            return

        self.smallest(node.left, order, q)

        # save to a queue to resolve the situation where the elements include duplication
        if len(q) == 0 or (len(q) < order and q[-1].value != node.value):
            q.append(node)

        self.smallest(node.right, order, q)

    def run(self):
        data1 = [9, 5, 13, 1, 8, 7, 10, 21, 18, 20]
        data2 = [1, 1, 1, 1, 1, 1]
        data3 = [1]
        data4 = [1, 1, 2, 2]
        data5 = [1, 2, 3, 4, 5, 6, 7]
        data6 = [1, 1, 2, 2, 3, 4, 4, 4, 5, 5, 6, 6, 7, 7]
        data7 = [9, 5, 13, 1, 8, 7, 10, 21, 18, 20, 9]
        for data in [data1, data2, data3, data4, data5, data6, data7]:
            self.root = Bst.Node()
            self.add_all(data)
            most_unique = []
            self.most_unique(self.root, None, 0, 0, most_unique)
            print(f"most_unique, input: {data}, output: {', '.join([str(p.value) for p in most_unique])}")

            q = []
            order = 2
            in_order = []
            self.smallest(self.root, order, q)
            self.in_order(self.root, in_order)
            ss = 'N/A'
            if len(q) == order:
                ss = q[order - 1].value
            print(f"second_smallest, in order: [{', '.join([str(p.value) for p in in_order])}], result: {ss}\n")
