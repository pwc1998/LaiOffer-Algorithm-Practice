package Algorithms;

import java.util.Arrays;
import java.util.List;

public class LinkedList {
    public static void main(String[] args) {
        LinkedList obj = new LinkedList();

        ListNode head = ListNode.fromArray(new int[] {2,2,2,2,3,2,2});
//        printBool(obj.isPalindrome(head));

        ListNode head1 = ListNode.fromArray(new int[] {1,2,6});
        ListNode head2 = ListNode.fromArray(new int[] {6, 3});
//        ListNode.print(obj.addTwoNumbers(head1, head2));

        printBool(obj.test());
        printArray(obj.testArray());
    }

    private static void printInt(int num) {
        System.out.println(num);
    }
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    private static void printList(List<Integer > list) {
        System.out.println(list.toString());
    }
    private static void printBool(Boolean bool) {
        System.out.println(bool);
    }
    private static void printString(String str) { System.out.println(str); }

    static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int value) {
            this.value = value;
        }

        public static ListNode fromArray(int[] values) {
            ListNode head = new ListNode(values[0]);
            ListNode curr = head;
            for(int i = 1; i < values.length; i++) {
                ListNode newNode = new ListNode(values[i]);
                curr.next = newNode;
                curr = curr.next;
            }
            return head;
        }

        public static void print(ListNode head) {
            ListNode curr = head;
            System.out.print("Linked List: ");
            while(curr != null) {
                System.out.print(curr.value + " -> ");
                curr = curr.next;
            }

            System.out.print("null\n");
        }

        public void print() {
            ListNode curr = this;
            System.out.print("Linked List: ");
            while(curr != null) {
                System.out.print(curr.value + " -> ");
                curr = curr.next;
            }

            System.out.print("null\n");
        }
    }


    public boolean isPalindrome(ListNode head) {
        // Write your solution here
        // Solution is two fold:
        // 1. find the middle point of the linked list
        // 2. reverse either one list (right one; because if odd number, we can omit the last node in left when comparing)
        // 3. make sure two list going afterward is exactly same. otherwise not palindrome
        // check input
        if(head == null || head.next == null) {
            return true;
        }
        ListNode middle = findMiddle(head);
        ListNode left = head, right = middle.next;
        right = reverse(right);
        ListNode.print(left);
//        ListNode.print(right);
        right.print();

        // comparing the two. Stop when right list nodes depleted.
        while(right != null) {
            if(left.value != right.value) return false;
            right = right.next;
            left = left.next;
        }

        return true;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode curr = head.next, last = head;
        head.next = null;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = last;
            last = curr;
            curr = next;
        }

        return last;  // which is the new head
    }



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Write your solution here
        String one = "", two = "";
        while(l1 != null) {
            one += l1.value;
            l1 = l1.next;
        }
        while(l2 != null) {
            two += l2.value;
            l2 = l2.next;
        }

//        printString(reverseString(one));
        int sum = Integer.parseInt(reverseString(one)) + Integer.parseInt(reverseString(two));
        String sumS = "" + sum;
//        printString(sumS);
        ListNode dummy = new ListNode(0);
        ListNode dummyH = dummy;
        for(int i = sumS.length() - 1; i >= 0; i--) {
            // 012
            // 243
            dummy.next = new ListNode(Integer.parseInt(sumS.substring(i, i+1)));
            dummy = dummy.next;
        }
        return dummyH.next;
    }


    private String reverseString(String str) {
        char[] c = str.toCharArray();
        for(int i = 0; i <= str.length()/2 - 1; i++) {
            char temp = c[i];
            c[i] = c[c.length-1-i];
            c[c.length-1-i] = temp;
        }
//        printString(Arrays.toString(c));
        return new String(c);
    }



    // A good explanation for java's objects passing that seems like pass-by-reference.
    // https://www.cs.virginia.edu/~jh2jf/courses/cs2110/java-pass-by-value.html
    boolean test() {
        Boolean a = true;
//        a = false;
        testChange(a);
        return a;
    }
    void testChange(Boolean bool) {
        bool = false;
    }

    int[] testArray() {
        int[] a = {1,2,3,4};
        changeArray(a);
        return a;
    }
    void changeArray(int[] a) {
        a[0] = 0;
    }
}
