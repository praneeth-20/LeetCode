class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Dummy head simplifies our code so we don't have to write special logic for the first node
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // Keep running as long as there are digits left in either list, OR if we have a leftover carry
        while (l1 != null || l2 != null || carry != 0) {
            // If one list is shorter, just treat its missing digits as 0
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            
            // Calculate the total sum for this column
            int sum = x + y + carry;
            
            // The carry for the next column (e.g., if sum is 14, carry becomes 1)
            carry = sum / 10;
            
            // The actual digit to place in the new node (e.g., if sum is 14, node gets 4)
            current.next = new ListNode(sum % 10);
            
            // Move our pointers forward
            current = current.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        // Return the actual head of the new list, skipping the dummy node
        return dummyHead.next;
    }
}