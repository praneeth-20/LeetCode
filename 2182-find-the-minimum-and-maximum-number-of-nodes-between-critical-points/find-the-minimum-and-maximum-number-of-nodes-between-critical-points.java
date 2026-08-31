/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstCrit = -1;
        int lastCrit = -1;
        int minDist = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;
        
        while (curr.next != null) {
            if ((curr.val > prev.val && curr.val > curr.next.val) || 
                (curr.val < prev.val && curr.val < curr.next.val)) {
                
                if (firstCrit == -1) {
                    firstCrit = index;
                } else {
                    minDist = Math.min(minDist, index - lastCrit);
                }
                lastCrit = index;
            }
            prev = curr;
            curr = curr.next;
            index++;
        }
        
        if (firstCrit == -1 || firstCrit == lastCrit) {
            return new int[]{-1, -1};
        }
        
        return new int[]{minDist, lastCrit - firstCrit};
    }
}