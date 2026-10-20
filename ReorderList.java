public class ReorderList {

    static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find the middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        ListNode prev = null;
        ListNode curr = slow.next;
        slow.next = null;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Step 3: Merge the two halves
        ListNode first = head;
        ListNode second = prev;

        while (second != null) {
            ListNode t1 = first.next;
            ListNode t2 = second.next;

            first.next = second;
            second.next = t1;

            first = t1;
            second = t2;
        }
    }

    static ListNode build(int[] nums) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int n : nums) {
            tail.next = new ListNode(n);
            tail = tail.next;
        }

        return dummy.next;
    }

    static String show(ListNode head) {
        StringBuilder sb = new StringBuilder();

        for (ListNode p = head; p != null; p = p.next) {
            sb.append(p.val);

            if (p.next != null) {
                sb.append(" -> ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] tests = {
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4},
            {1}
        };

        for (int[] t : tests) {
            ListNode head = build(t);

            System.out.println("Before: " + show(head));
            reorderList(head);
            System.out.println("After: " + show(head));
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}
