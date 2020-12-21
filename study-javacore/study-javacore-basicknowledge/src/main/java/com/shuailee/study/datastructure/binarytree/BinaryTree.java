package com.shuailee.study.datastructure.binarytree;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2019-11-27 21:38
 **/
public class BinaryTree {

    /**
     * 根节点
     */
    private TreeNode root;

    public BinaryTree(Object data) {
        this.root = new TreeNode(data);
    }

    public TreeNode addNode(TreeNode parentNode, Object data, boolean isleft) {
        if (parentNode == null) {
            throw new RuntimeException("父节点为空，无法添加子节点");
        }
        if (isleft && parentNode.lefTreeNode != null) {
            throw new RuntimeException("左子节点已经存在，添加失败");
        }
        if (!isleft && parentNode.rightNode != null) {
            throw new RuntimeException("右子节点已经存在，添加失败");
        }

        TreeNode newNode = new TreeNode(data);
        if (isleft) {
            parentNode.lefTreeNode = newNode;
        } else {
            parentNode.rightNode = newNode;
        }
        return newNode;
    }


    /**
     * 前序遍历 -中左右  中序遍历-左中右   后续遍历- 左右中
     * @param currNode
     */
    public void preOrder(TreeNode currNode) {
        if (currNode == null) {
            return;
        }
        System.out.print(currNode.value + " ");
        preOrder(currNode.lefTreeNode);
        preOrder(currNode.rightNode);
    }


    class TreeNode {

        public TreeNode(Object value) {

            this.value = value;
        }

        /**
         * 值
         */
        private Object value;

        /**
         * 左子树
         */
        private TreeNode lefTreeNode;

        /**
         * 右子树
         */
        private TreeNode rightNode;

    }


}
