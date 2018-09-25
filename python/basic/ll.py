#!/usr/local/bin/python3


class LL:
    def __init__(self):
        self.tail = None
        self.head = None

    def get(self, val):
        node = self.head
        while node:
            if node.val == val:
                return node
            node = node.next

    def add(self, val):
        if not self.head:
            self.head = LL.Node(val)
            self.tail = self.head
        else:
            self.tail.next = LL.Node(val)
            self.tail.next.prev = self.tail
            self.tail = self.tail.next

    def delete(self, val):
        node = self.get(val)
        if not node:
            print(f"Warning, node with value: {val} not found")
            return
        if node == self.head:
            self.head = node.next
            if self.head:
                self.head.prev = None
        elif node == self.tail:
            self.tail = node.prev
            self.tail.next = None
        else:
            node.prev.next = node.next
            node.next.prev = node.prev

    def reverse(self):
        """
        reverse
        """
        if not self.head:
            return

        self.tail = self.head

        cp = self.head
        pp = None
        np = cp.next

        while cp:
            cp.next = pp
            cp.prev = np
            pp = cp
            cp = np
            if np:
                np = np.next

        self.head = pp

    def rreverse(self, node):
        """
        reverse, use recursion
        """
        if not node:
            return

        if not node.prev:
            self.tail = node

        prev = node.prev
        node.prev = self.rreverse(node.next)

        if not node.next:
            self.head = node

        node.next = prev

        return node

    def loop(self, node):
        pass

    def print(self, msg):
        print(msg)
        node = self.head
        while node:
            print(node.val, end=", ")
            node = node.next
        if self.head:
            print(f"head: {self.head.val}, tail: {self.tail.val}")
        else:
            print('head: None, tail: None')
        print("\n")

    class Node:
        def __init__(self, val):
            self.val = val
            self.next = None
            self.prev = None

    def run(self):
        def new():
            test = [1, 2, 3, 4, 5, 6, 7, 8, 9]
            ll = LL()
            for e in test:
                ll.add(e)
            ll.print('Initial state:')
            return ll

        def delete():
            print("========== delete ==========")
            ll = new()
            ll.delete(1)
            ll.print('After delete 1')
            ll.delete(9)
            ll.print('After delete 9')
            ll.delete(5)
            ll.print('After delete 5')
            ll.delete(2)
            ll.delete(3)
            ll.delete(4)
            ll.delete(6)
            ll.print('After delete 2-6')
            ll.delete(7)
            ll.print('After delete 7')
            ll.delete(8)
            ll.print('After delete 8')

        def delete_reverse():
            print("========== delete_reverse ==========")
            ll = new()
            ll.reverse()
            ll.print('After reverse')
            ll.delete(1)
            ll.delete(2)
            ll.delete(3)
            ll.delete(4)
            ll.delete(5)
            ll.delete(6)
            ll.delete(7)
            ll.print('After delete 1-7')
            ll.reverse()
            ll.print('After reverse')
            ll.delete(8)
            ll.print('After delete 8')
            ll.reverse()
            ll.print('After reverse')
            ll.delete(9)
            ll.print('After delete 9')
            ll.reverse()
            ll.print('After reverse')

        def delete_rreverse():
            print("========== delete_rreverse ==========")
            ll = new()
            ll.rreverse(ll.head)
            ll.print('After rreverse')
            ll.delete(1)
            ll.delete(2)
            ll.delete(3)
            ll.delete(4)
            ll.delete(5)
            ll.delete(6)
            ll.delete(7)
            ll.print('After delete 1-7')
            ll.rreverse(ll.head)
            ll.print('After rreverse')
            ll.delete(8)
            ll.print('After delete 8')
            ll.rreverse(ll.head)
            ll.print('After rreverse')
            ll.delete(9)
            ll.print('After delete 9')
            ll.rreverse(ll.head)
            ll.print('After rreverse')

        delete()
        delete_reverse()
        delete_rreverse()
