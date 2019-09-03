package cn.ply.cloud.algorithmsvr.leetcode;

/**
 * @Author:ply
 * @Description:
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date: created in 2019/9/3
 * @Modified By:
 */
public class AddTwoNumbers {

    /**
     * 循环求解，将结果直接保存至l1，l2的节点中，节省空间消耗
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int m = 0;
        ListNode cur = new ListNode(0);
        ListNode listNode = cur;
        while (l1 != null || l2 != null || m != 0){
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            if (l1 != null) {
                cur.next = l1;
            } else if (l2 != null) {
                cur.next = l2;
            } else {
                cur.next = new ListNode(m);
            }
            cur = cur.next;
            int i = v1 + v2 + m;
            cur.val = i % 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            m = i / 10;
        }
        return listNode.next;
    }

    /**
     * 递归求解
     * @param l1
     * @param l2
     * @param m 进位
     * @return
     */
    private ListNode addTwoNumbers(ListNode l1, ListNode l2, Integer m){
        if (l1 == null && l2 == null && m == 0){
            return null;
        }
        int v1 = l1 == null ? 0 : l1.val;
        int v2 = l2 == null ? 0 : l2.val;
        l1 = l1 == null ? null : l1.next;
        l2 = l2 == null ? null : l2.next;
        int i = v1 + v2 + m;
        ListNode listNode = new ListNode(i % 10);
        listNode.next = addTwoNumbers(l1, l2, i / 10);
        return listNode;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
