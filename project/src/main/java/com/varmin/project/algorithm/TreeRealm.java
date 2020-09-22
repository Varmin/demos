package com.varmin.project.algorithm;

import com.varmin.project.base.BaseImpl;

import java.util.LinkedList;
import java.util.Queue;

/**
 * author：yang
 * created on：2020/9/8 10:52
 * description: 树
 */
public class TreeRealm extends BaseImpl {
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
        public Tree(int root){
            this.root = new Node(root);
        }
        public Tree(Node root){
            this.root = root;
        }

        // 广度优先遍历寻找空位置添加节点
        public Tree add(int value){
            Node tmp = new Node(value);
            //if(root == null) root = tmp;

            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node node = queue.poll();

                if (node.lChild == null) {
                    node.lChild = tmp;
                    System.out.println("Tree.add left = " + tmp.value + " --> 节点：" + node.value);
                    return this;
                }else if(node.rChild == null){
                    node.rChild = tmp;
                    System.out.println("Tree.add right = "+  tmp.value + " --> 节点：" + node.value);
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
    }

    private void printTree(Node node){
        if(node == null) return;
    }


    @Override
    public void run() {
        Tree tree = new Tree(0);
        for (int i = 1; i <= 6; i++) {
            tree.add(i);
        }
        printTree(tree.root);

        /**
         * 最大深度
         */
        int maxDep = maxDepth(tree.root);
        System.out.println("TreeRealm.run maxDep = " + maxDep);

        /**
         * 深度优先遍历：DFS，Depth First Search
         * 先序、中序、后序遍历
         */
        //先序：递归
        preOrder(tree.root);
        //先序：非递归
        preOrder2(tree.root);

        /**
         * BST
         */

    }



    /**
     ****************************DFS****************************
     */
    /**
     * 先序遍历
     */
    //递归
    private void preOrder(Node node) {
        if (node == null) return;
        if(node.value == 0) System.out.println("TreeRealm.preOrder: 先序遍历---：递归");
        System.out.println("TreeRealm.preOrder: "+node.value);
        preOrder(node.lChild);
        //System.out.println("TreeRealm.inOrder: "+node.value);
        preOrder(node.rChild);
        //System.out.println("TreeRealm.postOrder: "+node.value);
    }
    //非递归
    private void preOrder2(Node node) {

    }

    /**
     * 最大深度
     */
    private int maxDepth(Node root) {
        return root == null ? 0 : Math.max(maxDepth(root.lChild), maxDepth(root.rChild))  + 1;
    }
}
