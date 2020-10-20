package com.varmin.project.algorithm;

import com.varmin.project.base.BaseImpl;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value){
            this.val = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + val +
                    ", left =" + (left == null ? 0 : left.val) +
                    ", right =" + (right == null ? 0 : right.val) +
                    '}';
        }
    }

    class Tree{
        private TreeNode root;
        public Tree(){}
        public Tree(int root){
            this.root = new TreeNode(root);
        }
        public Tree(TreeNode root){
            this.root = root;
        }

        // 广度优先遍历寻找空位置添加节点
        public Tree add(int value){
            TreeNode tmp = new TreeNode(value);
            if(root == null){
                root = tmp;
                System.out.println("Tree.add 根节点："+ root.val);
                return this;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                if (node.left == null) {
                    node.left = tmp;
                    System.out.println("Tree.add left = " + tmp.val + " --> 根节点：" + node.val);
                    return this;
                }else if(node.right == null){
                    node.right = tmp;
                    System.out.println("Tree.add right = "+  tmp.val + " --> 根节点：" + node.val);
                    return this;
                }else {
                    // 当前节点已有子节点时，添加其子节点到queue遍历
                    queue.add(node.left);
                    queue.add(node.right);
                    System.out.println("Tree.add 遍历左右节点 ---> " + node);
                }
            }
            return this;
        }

        public String print(){
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                if (node.left == null) {
                }else if(node.right == null){
                }else {
                    queue.add(node.left);
                    queue.add(node.right);
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

        /**
         * 层序遍历
         */
        printLine("层序遍历");
        Tree tree3 = new Tree();
        for (int i : new int[]{1,2,3,4,5,6,7}) {
            tree3.add(i);
        }
        //DFS
        dfs(tree3.root);
        //BFS
        bfs(tree3.root);
        //层序遍历
        levelOrder(tree3.root);

        /**
         * 有序数组转为搜索二叉树
         */
        printLine("有序数组转为搜索二叉树");
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        TreeNode node = sortedArrayToBST(nums, 0, nums.length - 1);
        preOrder0(node);
    }

    /**
     ****************************有序数组转为搜索二叉树****************************
     *
     */
    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        System.out.println("TreeRealm.sortedArrayToBST: "+ left+", "+right +": "+mid+", "+ root.val);
        root.left = sortedArrayToBST(nums, left, mid -1);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }

    /**
     ****************************层序遍历****************************
     */
    // Deep First Search 深度优先遍历
    private void dfs(TreeNode root) {
        if (root == null) return;
        System.out.println("TreeRealm.dfs: "+ root.val);
        dfs(root.left);
        dfs(root.right);
    }

    // Breath First Search 广度优先遍历
    private void bfs(TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println("TreeRealm.bfs: "+ node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    private List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return new ArrayList();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //正常while循环一个一个出队，导致队列中有多层节点，无法区分每一层节点；使用for循环一次把一层的出完
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(level);
        }

        // 遍历结果
        for (List<Integer> level : res) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : level) {
                sb.append(integer).append(", ");
            }
            System.out.println("TreeRealm.levelBFS: " + sb.toString());
        }
        return res;
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
    private boolean checkBT(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            System.out.println("TreeRealm.checkBT true null&null");
            return true;
        }
        if (p == null || q == null) {
            System.out.println("TreeRealm.checkBT false: "+ p + ", " + q);
            return false;
        }
        System.out.println("TreeRealm.checkBT: "+ p.val + ", " + q.val);
        //满足条件：左右节点相等，且，子树节点镜像对称
        return p.val == q.val && checkBT(p.left, q.right) && checkBT(p.right, q.left);
    }

    private boolean checkBT2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode p = stack.pop();
            TreeNode q = stack.pop();

            if (q == null && p == null) continue;
            if (p == null || q == null) return false;
            if(p.val != q.val) return false;

            stack.push(p.left);
            stack.push(q.right);

            stack.push(p.right);
            stack.push(q.left);
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
    private boolean isBST(TreeNode root, Integer mix, Integer max) {
        if (root == null){
            //System.out.println("TreeRealm.isBST_1: "+mix+"-"+ "null" +"-"+max+" --> false");
            return true;
        }
        int value = root.val;
        //无论是左右子树，满足在区间内部
        if(mix != null && value <= mix){ return false;}
        if(max != null && value >= max){ return false;}

        // 递归传入合适的区间：
        // 左子树小于根节点value，
        // 右子树大于根节点value（其需要的根节点的根节点max，在判断左子树时已经传递）
        if (!isBST(root.left, mix, value)){
            //System.out.println("TreeRealm.isBST_4: "+mix+"-"+(root.lChild != null ? root.lChild.value : "null")+"-"+value+": lChild --> false");
            return false;
        }else {
            //System.out.println("TreeRealm.isBST_5:==== "+mix+"-"+(root.lChild != null ? root.lChild.value : "null")+"-"+value+": lChild --> true");
        }

        if (!isBST(root.right, value, max)) {
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
    private boolean isBST2(TreeNode root) {
        if(root == null) return true;
        long lastValue = Long.MIN_VALUE;
        Stack<TreeNode> stack = new Stack();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode tmp = stack.pop();
                root = tmp.right;

                if (tmp.val < lastValue) return false;
                lastValue = tmp.val;
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
    private void preOrder(TreeNode node, int order) {
        if (node == null) return;

        if(order == -1) orderList_1.add(node.val);
        preOrder(node.left, order);
        if(order == 0) orderList_1.add(node.val);
        preOrder(node.right, order);
        if(order == 1) orderList_1.add(node.val);
    }

    /**
     * 非递归，先序，中左右
     * 无论是左右子树节点，都可能是其它子树的根节点，每次循环都入栈左右节点
     * 栈：先入后出，先入右再入左，下次循环先出左子树（中左右）
     */
    private void preOrder_1(TreeNode node) {
        StringBuilder str = new StringBuilder();
        if(node != null){
            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()){
                TreeNode tmp = stack.pop();
                //栈：先进后出，先入栈右节点再左节点，下次取出的就是左节点了
                if (tmp.right != null) stack.push(tmp.right);
                if (tmp.left != null) stack.push(tmp.left);

                //System.out.println("TreeRealm.preOrder_1: add: "+ tmp.value);
                str.append(tmp.val).append(", ");
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
    private void preOrder0(TreeNode node) {
        StringBuilder str = new StringBuilder();

        if(node != null){
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || node != null){
                if (node != null) {
                    stack.add(node);
                    //System.out.println("TreeRealm.preOrder0: add: "+ node.value);
                    node = node.left;
                }else {
                    TreeNode tmp = stack.pop();
                    //System.out.println("TreeRealm.preOrder0: ------pop: "+ tmp.value);
                    str.append(tmp.val).append(", ");
                    node = tmp.right;
                }
            }
        }
        System.out.println("TreeRealm.preOrder0, 中序："+ str.toString());
    }

    /**
     * 非递归，后序: 左右中
     */
    private void preOrder1(TreeNode node) {
        if (node != null) {
            Stack<TreeNode> stackTmp = new Stack<TreeNode>();
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(node);
            while (!stack.isEmpty()) {
                TreeNode tmp = stack.pop();
                /**
                 * 先序：中左右，添加到stack时先添加r子树再l子树，先入后出就遍历为中左右
                 * 此处stack添加 中左右（先序为中右左），遍历时：中右左
                 * 添加到stackTmp：中右左，出栈顺序：左右中
                 */
                //注意这里和先序的l/r添加顺序
                if(tmp.left != null) stack.push(tmp.left);
                if(tmp.right != null) stack.push(tmp.right);
                //System.out.println("TreeRealm.preOrder1 push stack: "+ tmp.value);
                stackTmp.push(tmp);
            }

            StringBuilder strBuilder = new StringBuilder();
            while (!stackTmp.isEmpty()) {
                strBuilder.append(stackTmp.pop().val).append(", ");
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
    private int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right))  + 1;
    }
}
