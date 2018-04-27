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

    def run(self, data=(9, 5, 13, 1, 8, 7, 10, 21, 18, 20)):
        for i in data:
            self.add(i)
        all_path = []
        self.in_order(self.root, all_path)
        print(", ".join([str(p.value) for p in all_path]))

