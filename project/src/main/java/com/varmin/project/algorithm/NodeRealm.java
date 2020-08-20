package com.varmin.project.algorithm;

import com.varmin.project.base.BaseImpl;

import java.util.Arrays;

/**
 * author：yang
 * created on：2020/8/10 9:49
 * description: 链表相关
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
    private ListNode nodeCreator(int...args){
        ListNode node = new ListNode(args[0]);
        ListNode cur = node;
        for (int i = 1; i < args.length; i++) {
            ListNode tmp = new ListNode(args[i]);
            cur.next = tmp;
            cur = tmp;
        }
        System.out.println("NodeRealm.nodeCreator: "+node);
        return node;
    }

    @Override
    public void run() {
        // 反转链表：https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
        reverseList(nodeCreator(1,2,3,4,5));
        reverseList2(nodeCreator(1,2,3,4,5));

        // 合并两个有序链表: https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
        ListNode merge1 = mergeListNode(nodeCreator(1, 3, 5), nodeCreator(2, 3, 4, 5));
        System.out.println("NodeRealm.merge1: "+merge1);
        ListNode merge2 = mergeListNode2(nodeCreator(1,3,5), nodeCreator(2,3,4,5));
        System.out.println("NodeRealm.merge2: "+merge2);
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
        }else if(ln2 == null){
            return ln1;
        }else if(ln1.val < ln2.val){
            ln1.next = mergeListNode(ln1.next, ln2);
            return ln1;
        }else {
            ln2.next = mergeListNode(ln1, ln2.next);
            return ln2;
        }
    }

    private ListNode mergeListNode2(ListNode ln1, ListNode ln2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (ln1 != null && ln2 != null){
            if(ln1.val < ln2.val){
                pre.next = ln1;
                ln1 = ln1.next;
            }else {
                pre.next = ln2;
                ln2 = ln2.next;
            }
            pre = pre.next;
        }
        pre.next = ln1 == null ? ln2 : ln1;
        return head.next;
    }
    /**
     ****************************反转链表****************************
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
        System.out.println("NodeRealm.reverseList, after: "+pre);
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
        System.out.println("NodeRealm.reverseList2, after: "+cur);
        return cur;
    }
}

