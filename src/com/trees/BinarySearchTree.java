package com.trees;

public class BinarySearchTree {
    public static void main(String[] args) {
        int[] arr = {7, 2, -8, 9, 99, 0, 32};
        BST binarytree = new BST();

        for (int i = 0; i < arr.length; i++) {
            binarytree.addNode(new Node(arr[i]));
        }
        System.out.println("before deleting...");
        binarytree.inOrder();
        System.out.println("after deleteing...");
        binarytree.del(2);
        binarytree.del(-8);
        binarytree.del(32);
        binarytree.inOrder();


    }
}

class BST {

    private Node root;

    //add nodes
    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }

    //search node
    public Node searchNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchNode(value);
        }

    }

    //search parent node
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }

    }

    //traverse tree in in-order
    public void inOrder() {
        if (root == null) {
            System.out.println("empty tree");
        } else {
            root.inOrder();
        }

    }

    public int minLeft(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        del(node.value);
        return node.value;


    }


    /*
    1. delete leaf nodes
    2. delete middle nodes(with one child node)
    3. delete midlde nodes(with both children nodes)

    */

    public void del(int value) {
        if (root == null) {
            return;
        } else {

            //if such node does not exist
            Node target = searchNode(value);
            if (target == null) {
                return;
            }
            //if target node does not have a parent node
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);


            //delete leaf nodes
            if (target.left == null && target.right == null) {
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            }

            //delete middle node with two children nodes
            //find the smallest value on the target's right
            else if (target.left != null && target.right != null) {
                int minLeft = minLeft(target.right);
                target.value = minLeft;


            }

            //delete middle node with one child node
            else {

                if (target.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = target.left;
                        } else {
                            parent.right = target.left;
                        }
                    } else {
                        root = target.left;
                    }
                } else {
                    if (parent != null) {

                        if (parent.left.value == value) {
                            parent.left = target.right;
                        } else {
                            parent.right = target.right;
                        }
                    } else {
                        root = target.right;
                    }
                }

            }
        }


    }
}

    //construct node class
    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return
                    "value = " + value +
                            " --> ";
        }

        //add nodes
        public void addNode(Node node) {
            if (node.value < this.value) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.addNode(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.addNode(node);
                }
            }
        }

        //in-order search
        public void inOrder() {

            if (this.left != null) {
                this.left.inOrder();
            }
            System.out.print(this);

            if (this.right != null) {
                this.right.inOrder();
            }
        }


        //search node

        public Node searchNode(int value) {
            if (this.value == value) {
                return this;
            } else if (value < this.value) {
                if (this.left == null) {
                    return null;
                }
                return this.left.searchNode(value);

            } else {
                if (this.right == null) {
                    return null;
                }
                return this.right.searchNode(value);

            }


        }

        //search parent node with value
        public Node searchParent(int value) {
            if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
                return this;
            } else if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }


        }

        //search the parent of the node
        public Node Parent(Node node) {
            if ((this.left != null && this.left == node) || (this.right != null && this.right == node)) {
                return this;

            } else if (node.value < this.value && this.left != null) {
                return this.left.Parent(node);
            } else if (node.value >= this.value && this.right != null) {
                return this.right.Parent(node);
            } else {
                return null;
            }
        }


    }