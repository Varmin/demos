package com.varmin.project.algorithm;

import com.varmin.project.base.BaseImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * author：yang
 * created on：2020/9/8 10:52
 * description: 树


 * 树概念、分类、存储表示
 * 树操作：排序、搜索、添加、删除、遍历、深度
 *        递归、迭代、栈
 * 分类：
    * 二叉树：分类




 */
public class TreeRealm extends BaseImpl {
    private ArrayList<Integer> orderList_1;

    class Node{
        public int value;
        public Node lChild;
        public Node rChild;
        public Node(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left =" + (lChild == null ? 0 : lChild.value) +
                    ", right =" + (rChild == null ? 0 : rChild.value) +
                    '}';
        }
    }

    class Tree{
        private Node root;
        public Tree(){}
        public Tree(int root){
            this.root = new Node(root);
        }
        public Tree(Node root){
            this.root = root;
        }

        // 广度优先遍历寻找空位置添加节点
        public Tree add(int value){
            Node tmp = new Node(value);
            if(root == null){
                root = tmp;
                System.out.println("Tree.add 根节点："+ root.value);
                return this;
            }

            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                Node node = queue.poll();

                if (node.lChild == null) {
                    node.lChild = tmp;
                    System.out.println("Tree.add left = " + tmp.value + " --> 根节点：" + node.value);
                    return this;
                }else if(node.rChild == null){
                    node.rChild = tmp;
                    System.out.println("Tree.add right = "+  tmp.value + " --> 根节点：" + node.value);
                    return this;
                }else {
                    // 当前节点已有子节点时，添加其子节点到queue遍历
                    queue.add(node.lChild);
                    queue.add(node.rChild);
                    System.out.println("Tree.add 遍历左右节点 ---> " + node);
                }
            }
            return this;
        }

        public String print(){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                Node node = queue.poll();

                if (node.lChild == null) {
                }else if(node.rChild == null){
                }else {
                    queue.add(node.lChild);
                    queue.add(node.rChild);
                }
            }

            return "";
        }
    }



    @Override
    public void run() {
        printLine("Tree 添加节点");
        Tree tree = new Tree();
        for (int i = 1; i <= 7; i++) {
            tree.add(i);
        }

        /**
         * 深度优先遍历：DFS，Depth First Search
         * 先序、中序、后序遍历
         */
        printLine("树的遍历");
        //先序
        orderList_1 = new ArrayList();
        preOrder(tree.root, -1);
        System.out.println("TreeRealm.run，      先序: "+ orderList_1);
        preOrder_1(tree.root);
        //中序
        orderList_1.clear();
        preOrder(tree.root, 0);
        System.out.println("TreeRealm.run，      中序: "+ orderList_1);
        preOrder0(tree.root);

        //后序
        orderList_1.clear();
        preOrder(tree.root, 1);
        System.out.println("TreeRealm.run，      后序: "+ orderList_1);
        preOrder1(tree.root);

        /**
         * 最大深度
         */
        printLine("深度");
        int maxDep = maxDepth(tree.root);
        System.out.println("TreeRealm.run maxDep = " + maxDep);

        /**
         * 判断搜索二叉树
         */
        printLine("判断搜索二叉树");
        Tree treeBst = new Tree();
        for (int i : new int[]{5,3,7,2,4,6,8}) {//4(true)、9(false)
            treeBst.add(i);
        }
        preOrder0(treeBst.root);
        System.out.println("TreeRealm.run, ====isBST====: "+ isBST(treeBst.root, null, null));
        System.out.println("TreeRealm.run, ====isBST2====: "+ isBST2(treeBst.root));

        /**
         * 对称二叉树
         */
        printLine("对称二叉树");
        Tree tree2 = new Tree();
        for (int i : new int[]{1,2,2,3,4,4,3}) {
            tree2.add(i);
        }
        System.out.println("TreeRealm.run result: " + checkBT(tree2.root, tree2.root));
        System.out.println("TreeRealm.run result: " + checkBT2(tree2.root));
    }

    /**
     ****************************判断对称二叉树****************************
     * 根节点的左右子树互为镜像对称（注意：不是每个子树的左右节点镜像）
     */

    /**
     * 递归
     * 1. 左右节点为null
     * 2. 左右子树镜像对称
     */
    private boolean checkBT(Node p, Node q) {
        if (p == null && q == null) {
            System.out.println("TreeRealm.checkBT true null&null");
            return true;
        }
        if (p == null || q == null) {
            System.out.println("TreeRealm.checkBT false: "+ p + ", " + q);
            return false;
        }
        System.out.println("TreeRealm.checkBT: "+ p.value + ", " + q.value);
        //满足条件：左右节点相等，且，子树节点镜像对称
        return p.value == q.value && checkBT(p.lChild, q.rChild) && checkBT(p.rChild, q.lChild);
    }

    private boolean checkBT2(Node root) {
        if (root == null) return true;
        Stack<Node> stack = new Stack();
        stack.push(root.lChild);
        stack.push(root.rChild);
        while (!stack.isEmpty()) {
            Node p = stack.pop();
            Node q = stack.pop();

            if (q == null && p == null) continue;
            if (p == null || q == null) return false;
            if(p.value != q.value) return false;

            stack.push(p.lChild);
            stack.push(q.rChild);

            stack.push(p.rChild);
            stack.push(q.lChild);
        }
        return true;
    }


    /**
     ****************************判断搜索二叉树****************************
     二叉搜索树（Binary Search Tree），（又：二叉查找树，二叉排序树）
     它或者是一棵空树，或者是具有下列性质的二叉树：
        * 若它的左子树不空，则左子树上`所有`结点的值均小于它的`根结点`的值；
        * 若它的右子树不空，则右子树上`所有`结点的值均大于它的`根结点`的值；
        * (注意：节点的根节点，有可能是其它节点的左/右节点。根节点，并不是只最上层的那个节点)
        * 它的左、右子树也分别为二叉搜索树。
     */

    /**
     如何判断:
     * 左子树小于根节点
     * 右子树大于根节点，且小于根节点的根节点（右子树相对于根节点的根节点，属于其左子树，所以应小于根节点的根节点）
     */

    /**
     * 递归判断
     *
     * 设置一个区间（l,r）：
     *   左子树的根节点为 r值
     *   右子树的根节点为l值，右子树根节点的根节点为r值
     */
    private boolean isBST(Node root, Integer mix, Integer max) {
        if (root == null){
            //System.out.println("TreeRealm.isBST_1: "+mix+"-"+ "null" +"-"+max+" --> false");
            return true;
        }
        int value = root.value;
        //无论是左右子树，满足在区间内部
        if(mix != null && value <= mix){ return false;}
        if(max != null && value >= max){ return false;}

        // 递归传入合适的区间：
        // 左子树小于根节点value，
        // 右子树大于根节点value（其需要的根节点的根节点max，在判断左子树时已经传递）
        if (!isBST(root.lChild, mix, value)){
            //System.out.println("TreeRealm.isBST_4: "+mix+"-"+(root.lChild != null ? root.lChild.value : "null")+"-"+value+": lChild --> false");
            return false;
        }else {
            //System.out.println("TreeRealm.isBST_5:==== "+mix+"-"+(root.lChild != null ? root.lChild.value : "null")+"-"+value+": lChild --> true");
        }

        if (!isBST(root.rChild, value, max)) {
            //System.out.println("TreeRealm.isBST_6: "+mix+"-"+(root.rChild != null ? root.rChild.value : "null")+"-"+value+": rChild --> false");
            return false;
        }else {
            //System.out.println("TreeRealm.isBST_7:==== "+mix+"-"+(root.rChild != null ? root.rChild.value : "null")+"-"+value+": rChild --> true");
        }
        //System.out.println("TreeRealm.isBST_@@@@: "+mix+"-"+value+"-"+max+" --> true");
        return true;
    }

    /**
     * 中序遍历正好符合二叉搜索树的排序规则，对比当前和上一个节点的值
     */
    private boolean isBST2(Node root) {
        if(root == null) return true;
        long lastValue = Long.MIN_VALUE;
        Stack<Node> stack = new Stack();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.lChild;
            } else {
                Node tmp = stack.pop();
                root = tmp.rChild;

                if (tmp.value < lastValue) return false;
                lastValue = tmp.value;
            }
        }
        return true;
    }

    /**
     ****************************DFS****************************
     */
    /**
     * 遍历：-1先序，0中序，1后序
     *
     * lChild、rChild既是子节点，又是父节点，不用考虑子/父节点，只考虑顺序
     */
    //递归
    private void preOrder(Node node, int order) {
        if (node == null) return;

        if(order == -1) orderList_1.add(node.value);
        preOrder(node.lChild, order);
        if(order == 0) orderList_1.add(node.value);
        preOrder(node.rChild, order);
        if(order == 1) orderList_1.add(node.value);
    }

    /**
     * 非递归，先序，中左右
     * 无论是左右子树节点，都可能是其它子树的根节点，每次循环都入栈左右节点
     * 栈：先入后出，先入右再入左，下次循环先出左子树（中左右）
     */
    private void preOrder_1(Node node) {
        StringBuilder str = new StringBuilder();
        if(node != null){
            Stack<Node> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()){
                Node tmp = stack.pop();
                //栈：先进后出，先入栈右节点再左节点，下次取出的就是左节点了
                if (tmp.rChild != null) stack.push(tmp.rChild);
                if (tmp.lChild != null) stack.push(tmp.lChild);

                //System.out.println("TreeRealm.preOrder_1: add: "+ tmp.value);
                str.append(tmp.value).append(", ");
            }
            System.out.println("TreeRealm.preOrder_1 先序: "+ str.toString());
        }
    }

    //非递归，中序，左中右

    /**
     * 左：不断往左子树方向走，放入栈中
     * 中：当左子树走到头时，从栈中取出当前节点
     * 右：从栈中取出根节点往右子树方向走，再进行重复步骤
     */
    private void preOrder0(Node node) {
        StringBuilder str = new StringBuilder();

        if(node != null){
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || node != null){
                if (node != null) {
                    stack.add(node);
                    //System.out.println("TreeRealm.preOrder0: add: "+ node.value);
                    node = node.lChild;
                }else {
                    Node tmp = stack.pop();
                    //System.out.println("TreeRealm.preOrder0: ------pop: "+ tmp.value);
                    str.append(tmp.value).append(", ");
                    node = tmp.rChild;
                }
            }
        }
        System.out.println("TreeRealm.preOrder0, 中序："+ str.toString());
    }

    /**
     * 非递归，后序: 左右中
     */
    private void preOrder1(Node node) {
        if (node != null) {
            Stack<Node> stackTmp = new Stack<Node>();
            Stack<Node> stack = new Stack<Node>();
            stack.push(node);
            while (!stack.isEmpty()) {
                Node tmp = stack.pop();
                /**
                 * 先序：中左右，添加到stack时先添加r子树再l子树，先入后出就遍历为中左右
                 * 此处stack添加 中左右（先序为中右左），遍历时：中右左
                 * 添加到stackTmp：中右左，出栈顺序：左右中
                 */
                //注意这里和先序的l/r添加顺序
                if(tmp.lChild != null) stack.push(tmp.lChild);
                if(tmp.rChild != null) stack.push(tmp.rChild);
                //System.out.println("TreeRealm.preOrder1 push stack: "+ tmp.value);
                stackTmp.push(tmp);
            }

            StringBuilder strBuilder = new StringBuilder();
            while (!stackTmp.isEmpty()) {
                strBuilder.append(stackTmp.pop().value).append(", ");
            }
            System.out.println("TreeRealm.preOrder1  后序："+ strBuilder.toString());
        }
    }

    /**
     ****************************树深度****************************
     */
    /**
     * 最大深度
     */
    private int maxDepth(Node root) {
        return root == null ? 0 : Math.max(maxDepth(root.lChild), maxDepth(root.rChild))  + 1;
    }
}
