package com.varmin.project.algorithm;

import com.varmin.project.base.BaseImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * author：yang
 * created on：2020/8/10 9:49
 * description: 链表相关: 遍历、递归、双指针、快慢指针、哑节点
 */
public class NodeRealm extends BaseImpl {
    class ListNode {
        int val;

        ListNode(int x) {
            val = x;
        }

        ListNode next;

        @Override
        public String toString() {
            return "{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 链表生成
     */
    private ListNode nodeCreator(int... args) {
        ListNode node = new ListNode(args[0]);
        ListNode cur = node;
        for (int i = 1; i < args.length; i++) {
            ListNode tmp = new ListNode(args[i]);
            cur.next = tmp;
            cur = tmp;
        }
        System.out.println("NodeRealm.nodeCreator: " + node);
        return node;
    }

    @Override
    public void run() {
        // 反转链表：https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
        reverseList(nodeCreator(1, 2, 3, 4, 5));
        reverseList2(nodeCreator(1, 2, 3, 4, 5));
        printLine();

        // 合并两个有序链表: https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
        ListNode merge1 = mergeListNode(nodeCreator(1, 3, 5), nodeCreator(2, 3, 4, 5));
        System.out.println("NodeRealm.merge1: " + merge1);
        ListNode merge2 = mergeListNode2(nodeCreator(1, 3, 5), nodeCreator(2, 3, 4, 5));
        System.out.println("NodeRealm.merge2: " + merge2);
        printLine();

        // 删除链表中的节点：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/solution/
        ListNode dNode = nodeCreator(1, 2, 3, 4, 5);
        deleteNode(dNode, dNode.next.next);
        ListNode dNode2 = nodeCreator(1, 2, 3, 4, 5);
        deleteNode2(dNode2.next.next);
        System.out.println("NodeRealm.delNode2: " + dNode2);
        printLine();

        // 删除链表的倒数第N个节点：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-by-l/
        ListNode dNode3 = nodeCreator(1, 2, 3, 4, 5, 6, 7, 8, 9);
        deleteNode3(dNode3, 2);
        ListNode dNode4 = nodeCreator(1, 2, 3);
        deleteNode4(dNode4, 3);

        // 判断环形链表：https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode/
        ListNode ln = nodeCreator(1, 2, 3, 4, 5, 6, 7, 8, 9);
        ListNode lnTmp = ln;
        while (lnTmp.next != null){
            lnTmp = lnTmp.next;
            if(lnTmp.val == 9) {
                lnTmp.next = ln.next.next.next.next;
                break;
            }
        }
        /*
        //输出查看循环位置
        lnTmp = ln;
        while (lnTmp.next != null){
            lnTmp = lnTmp.next;
            System.out.println("NodeRealm.run: "+lnTmp.val);
        }*/
        hasCycleOfHashCode(ln);
        hasCycleOfRun(ln);
    }

    private boolean hasCycleOfRun(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null && fast.next.next != null){
            if(slow == fast) {
                System.out.println("NodeRealm.hasCycleOfRun: "+slow.val);
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    private boolean hasCycleOfHashCode(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null){
            if (set.contains(head)) {
                System.out.println("NodeRealm.hasCycleOfHashCode: "+ head.val);
                return true;
            }else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     ****************************删除链表的第N个节点****************************
     */

    /**
     * 两次遍历：第一次得到链表长度，第二次删除节点
     */
    private void deleteNode3(ListNode head, int rIndex) {
        // 链表长度
        int length = 0;
        ListNode tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        System.out.println("NodeRealm.deleteNode3： length=" + length + ", rIndex=" + rIndex + ", index=" + (length - rIndex));
        length -= rIndex;

        //哑节点：遍历操作需要一个起始点时，方便计算
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (length > 0) {
            length--;
            dummy = dummy.next;
        }
        dummy.next = dummy.next.next;
        System.out.println("NodeRealm.deleteNode3: " + head);
    }

    /**
     * 双指针遍历：以往的双指针用来记录的，此处的双指针用来隔开n个值，相当于卡尺
     */
    private void deleteNode4(ListNode head, int rIndex) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode tmp = dummy;
        ListNode tmp2 = dummy;
        for (int i = 0; i < rIndex + 1; i++) {
            tmp = tmp.next;
        }
        while (tmp != null) {
            tmp = tmp.next;
            tmp2 = tmp2.next;
        }
        tmp2.next = tmp2.next.next;
        System.out.println("NodeRealm.deleteNode4: resutl: " + dummy.next);
    }

    /**
     ****************************删除链表节点****************************
     */
    /**
     * 双指针
     *
     * @param listNode 原链表
     * @param delNode  要删除的节点
     */
    private void deleteNode(ListNode listNode, ListNode delNode) {
        ListNode pre = null;
        ListNode cur = listNode;
        while (cur.next != null) {
            if (cur.val == delNode.val) {
                pre.next = cur.next;
                break;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        System.out.println("NodeRealm.deleteNode: " + listNode);
    }

    /**
     * 直接传入的就是被删除的节点
     *
     * @param delNode 要删除的节点
     */
    private void deleteNode2(ListNode delNode) {
        // delNode是引用类型，函数实参是引用传递，所以改变delNode引用是无效的，需要改变delNode属性的值
        //delNode = delNode.next;
        delNode.val = delNode.next.val;
        delNode.next = delNode.next.next;
    }

    /**
     ****************************合并两个有序数组****************************
     */
    /**
     * 递归：链表不要想成一个“集合”，它就是一个数据结构，只不过可以找到它的next节点
     * 作比较时，就是单一的两个node节点比较。既然mergeListNode方法每次返回min节点，那就使用递归连续调用
     * 时间复杂度：O(n+m), 空间复杂度：O(n+m)
     */
    private ListNode mergeListNode(ListNode ln1, ListNode ln2) {
        if (ln1 == null) {
            return ln2;
        } else if (ln2 == null) {
            return ln1;
        } else if (ln1.val < ln2.val) {
            ln1.next = mergeListNode(ln1.next, ln2);
            return ln1;
        } else {
            ln2.next = mergeListNode(ln1, ln2.next);
            return ln2;
        }
    }

    private ListNode mergeListNode2(ListNode ln1, ListNode ln2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (ln1 != null && ln2 != null) {
            if (ln1.val < ln2.val) {
                pre.next = ln1;
                ln1 = ln1.next;
            } else {
                pre.next = ln2;
                ln2 = ln2.next;
            }
            pre = pre.next;
        }
        pre.next = ln1 == null ? ln2 : ln1;
        return head.next;
    }

    /**
     * ***************************反转链表****************************
     */
    // 双指针迭代
    private ListNode reverseList(ListNode listNode) {
        // 定义双指针
        ListNode pre = null;
        ListNode cur = listNode;
        ListNode tmp;
        while (cur != null) {
            //记录下一个节点，操作之后备用
            tmp = cur.next;
            //改变当前节点指向
            cur.next = pre;
            //重置pre、cur进行循环
            pre = cur;
            cur = tmp;
        }
        System.out.println("NodeRealm.reverseList, after: " + pre);
        return pre;
    }

    // 递归
    private ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //重点：在返回处，此时cur是最后一个节点，head是前一个节点。
        //并且，cur未变，一直返回，到最前一个节点返回的cur还是5
        ListNode cur = reverseList2(head.next);
        //后一个节点指向前一个
        head.next.next = head;
        //防止链表循环，需要将head.next设置为空
        head.next = null;
        System.out.println("NodeRealm.reverseList2, after: " + cur);
        return cur;
    }

}

