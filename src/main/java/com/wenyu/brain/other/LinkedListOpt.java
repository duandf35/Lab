package com.wenyu.brain.other;

import com.wenyu.brain.CanRun;

/**
 * @author Wenyu
 * @since 11/18/16
 */
public class LinkedListOpt implements CanRun {

    @Override
    public void run() {
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n5 = new Node<>(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println("Before reverse: " + toString(n1));

        Node<Integer> head = reverse(n1);

        System.out.println("1st reverse: " + toString(head));

        head = reverse2(head);

        System.out.println("2nd reverse: " + toString(head));
    }

    /**
     * 1. Three pointers.
     *
     *   p1   p2    p3
     *        [] -> [] -> []
     *
     *        p1    p2    p3
     *        [] <- [] -> []
     *
     *              p1    p2    p3
     *        [] <- [] <- []
     *
     * @param head the head
     * @return the new head
     */
    private static Node<Integer> reverse(Node<Integer> head) {
        Node<Integer> newHead = head;

        if (head != null) {
            Node<Integer> p1, p2 = head, p3 = head.next;

            // to avoid loop
            head.next = null;

            while (p3 != null) {
                // move
                p1 = p2;
                p2 = p3;
                p3 = p3.next;

                // reverse
                p2.next = p1;
            }

            newHead = p2;
        }

        return newHead;
    }

    /**
     * 2. Recursion.
     *
     * @param head the head
     * @return the new head
     */
    private static Node<Integer> reverse2(Node<Integer> head) {
        if (head.next == null) {
            return head;
        }

        Node<Integer> next = head.next;
        // get the new head
        Node<Integer> newHead = reverse2(next);
        // reverse
        next.next = head;

        // avoid the loop, for any node except the old head
        // this operation is unnecessary since 'head' will
        // be the 'head.next' in the upper recursion which
        // is re-assign the 'head' of the upper recursion
        head.next = null;

        return newHead;
    }

    private class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private static String toString(Node<Integer> head) {
        Node<Integer> start = head;
        StringBuilder str = new StringBuilder();

        while (start.next != null) {
            str.append(start.value);
            str.append(" -> ");
            start = start.next;
        }

        str.append(start.value);
        str.append(" -> null ");
        return str.toString();
    }
}
