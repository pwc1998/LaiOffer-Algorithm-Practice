package Algorithms;

import apple.laf.JRSUIUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class TreeNode {
    int key;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    TreeNode(int value, TreeNode left, TreeNode right) {
        this.key = value;
        this.left = left;
        this.right = right;
    }

    TreeNode(int value) {
        this.key = value;
    }
}
class BTree {
    TreeNode root;
}

public class BinaryTree {
    public static void main(String[] args) {
        BinaryTree obj = new BinaryTree();
        /*
             10
            /  \
           5    15
          / \   / \
         2  7  12  20
        / \
     null  null
         */
        TreeNode two = new TreeNode(2, null, null);
        TreeNode seven = new TreeNode(7, null, null);
        TreeNode twelve = new TreeNode(12, null, null);
        TreeNode twenty = new TreeNode(20, null, null);
        TreeNode five = new TreeNode(5, two, seven);
        TreeNode fifteen = new TreeNode(15, twelve, twenty);
        TreeNode ten = new TreeNode(10, five, fifteen);
        two.parent = five;
        seven.parent = five;
        twelve.parent = fifteen;
        twenty.parent = fifteen;
        five.parent = ten;
        fifteen.parent = ten;
        ten.parent = null;

//        TreeNode one = new TreeNode(1, null, null);
//        TreeNode three = new TreeNode(3, null, null);
//        TreeNode two = new TreeNode(2, one, three);

        BTree tree = new BTree();
//        tree.root = ten;
//        tree.root = two;

        LinkedList<Integer> list = new LinkedList<>();

//        printList(obj.preOrder(tree.root));
//        printList(obj.inOrder(tree.root));
//        printList(obj.postOrder(tree.root));
//
//        printInt(obj.findHeight(tree.root));
//
//        System.out.println(obj.isBalanced(tree.root));
//        System.out.println(obj.isSymmetric(tree.root));
//        System.out.println(obj.isSymmetric(two));
//        printInt(111111);
//        System.out.println(obj.isTweakedIdentical(ten, ten));
//        printBool(obj.isBST(tree.root));
//        printList(obj.getRange(tree.root, 6, 15));
//
//        System.out.println(obj.searchIterative(tree.root, 13));

//        obj.insertIterative(tree.root, 8);
//        printList(obj.inOrder(tree.root));


//        printList(obj.inOrder(obj.deleteIterative(tree.root, 1)));

        // class 6
//        System.out.println(obj.layerByLayer(tree.root));
        printBool(obj.isComplete(tree.root));
    }


    private static void printInt(int num) {
        System.out.println(num);
    }

    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    private static void printList(List<Integer > list) {
        System.out.println(list.toString());
    }
    private static void printBool(Boolean bool) {
        System.out.println(bool);
    }

    public List<Integer> preOrder(TreeNode root) {
        // base case
        if(root == null) {
            return new LinkedList<>();
        }

        // recursive step
        LinkedList<Integer> result = new LinkedList<>();
        result.add(root.key);

        result.addAll(preOrder(root.left));
        result.addAll(preOrder(root.right));

        return result;
    }

    public List<Integer> inOrder(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        inOrderHelper(root, result);
        return result;
    }
    private void inOrderHelper(TreeNode root, LinkedList<Integer> result) {
        // base case
        if(root == null) {
            return;
        }

        // recursive step
        inOrderHelper(root.left, result);
        result.add(root.key);
        inOrderHelper(root.right, result);
    }

    List<Integer> postOrder(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        postOrderHelper(root, result);
        return result;
    }
    void postOrderHelper(TreeNode root, LinkedList<Integer> result) {
        if(root == null) {
            return;
        }

        postOrderHelper(root.left, result);
        postOrderHelper(root.right, result);
        result.add(root.key);
    }

    int findHeight(TreeNode root) {
        // base case
        if(root == null) {
            return 0;
        }

        // recursive step
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    boolean isBalanced(TreeNode root) {
//        LinkedList<Boolean> balanced = new LinkedList<>();
//        isBalancedHelper(root, balanced);
//        if(balanced.peek() == null) {
//            return true;
//        }
//        else {
//            return false;
//        }
//        return balanced.peek()==null ? true : false;
//        return balanced.peek() == null;
        return isBalancedHelper(root) != -1;
    }
    int isBalancedHelper(TreeNode root, List<Boolean> balanced) {  // modified find Height, with boolean
        // base case
        if(root == null) {
            return 0;
        }

        // recursive step
        int leftHeight = isBalancedHelper(root.left, balanced);
        int rightHeight = isBalancedHelper(root.right, balanced);
        if(Math.abs(leftHeight - rightHeight) > 1) {  // not balanced
            balanced.add(false);
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // -1 means unbalanced
    int isBalancedHelper(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftHeihgt = isBalancedHelper(root.left);
        int rightHeight = isBalancedHelper(root.right);

        if(Math.abs(leftHeihgt - rightHeight) > 1 || leftHeihgt == -1 || rightHeight == -1) {
            return -1;
        }
        else {
            return Math.max(leftHeihgt, rightHeight) + 1;
        }
    }


    boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }
    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        // base case
        if(left == null && right == null) {
            return true;
        }
        if(left != null && right != null && left.key == right.key) {  // compare at this level works
            return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);  // outer and inner
        }

        return false;
    }

    // recursively compare if their subtree are the same.
    boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        // base case
        if(one == null && two == null) {
            return true;
        }

        if(one != null && two != null && one.key == two.key) {
            boolean caseOne = isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right);
            boolean caseTwo = isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left);
            return caseOne || caseTwo;
        }

        return false;
    }

    boolean isBST(TreeNode root) {
        if(root == null) {
            return true;
        }

        return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isBSTHelper(TreeNode root, int min, int max) {
        // base case
        if(root == null) {
            return true;
        }

        // recursive step.  1. processing current level. 2. call on next level
        if(root.key <= min || root.key >= max) {
            return false;
        }

        return isBSTHelper(root.left, min, root.key) && isBSTHelper(root.right, root.key, max);
    }

    List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> result = new LinkedList<>();
        getRangeHelper(root, min, max, result);
        return result;
    }
    private void getRangeHelper(TreeNode root, int min, int max, List<Integer> result) {
        // base case
        if(root == null) {
            return;
        }

        // recursive step
        if(root.key > max) {
            getRangeHelper(root.left, min, max, result);
        }
        else if(root.key < min) {
            getRangeHelper(root.right, min, max, result);
        }
        else {
            getRangeHelper(root.left, min, max, result);
            result.add(root.key);
            getRangeHelper(root.right, min, max, result);
        }
    }

    // find target in BST. Return null when no match.
//    TreeNode search(TreeNode root, int target) {
//        // base case
//        if(root == null) {
//            return null;
//        }
//
//        // recursive step
//        if(root.key < target) {
//            return search(root.right, target);
//        }
//
//        if(root.key > target) {
//            return search(root.left, target);
//        }
//
//        return root;  // case when it's equal.
//    }
    TreeNode search(TreeNode root, int target) {
        if(root == null || root.key == target) {
            return root;
        }

        return search(root.key > target? root.left : root.right, target);
    }

    TreeNode searchIterative(TreeNode root, int target) {
        TreeNode currNode = root;

        while(currNode != null) {
            if(currNode.key == target) {
                break;
            }

            if(currNode.key < target) {
                currNode = currNode.right;
            }
            else{
                currNode = currNode.left;
            }
        }

        return currNode;
    }

    TreeNode insert(TreeNode root, int key) {
        // base case
        if(root == null) {
            return new TreeNode(key);
        }

        // recursive step
        if(root.key < key) {
            root.right = insert(root.right, key);
        }
        else if (root.key > key) {
            root.left = insert(root.left, key);
        }

        // when it's equal; no need to create new node
        return root;
    }

    TreeNode insertIterative(TreeNode root, int key) {
        TreeNode currNode = root, newNode = new TreeNode(key, null, null);
        if(root == null) {
            return newNode;
        }

        // 1. existed
        // 2. insert to left
        // 3. insert to right
        while(true) {
            if(currNode.key == key) {
                return root;
            }

            if(currNode.key < key) {
                if(currNode.right != null) {
                    currNode = currNode.right;
                }
                else {
                    currNode.right = newNode;
                    return root;
                }
            }
            else {
                if(currNode.left != null) {
                    currNode = currNode.left;
                }
                else {
                    currNode.left = newNode;
                    return root;
                }
            }
        }
    }

//    TreeNode delete(TreeNode root, int key) {
//        // base case
//        if(root == null) {
//            return null;
//        }
//
//        // recursive
//        if(root.left != null && root.left.key == key) {
//
//        }
//    }

    TreeNode deleteIterative(TreeNode root, int key) {
        TreeNode currNode = root, prevNode = null;
        int a = 0;
        boolean prevLeft = true;
        while(currNode != null) {
            if(currNode.key == key) {
                TreeNode left = currNode.left, right = currNode.right;
                boolean hasLeft = (currNode.left != null);
                boolean hasRight = (currNode.right != null);
                if(!hasLeft && !hasRight) {
                    if(prevNode == null) {
                        return null;
                    }
                    else {
                        if(prevLeft) {
                            prevNode.left = null;
                        }
                        else {
                            prevNode.right = null;
                        }
                    }
                }
                else if(!hasLeft && hasRight) {
                    if(prevNode == null) {
                        return right;
                    }
                    else {
                        if(prevLeft) {
                            prevNode.left = right;
                        }
                        else {
                            prevNode.right = right;
                        }
//                        currNode = right;
                    }
                }
                else if(hasLeft && !hasRight) {
                    if(prevNode == null) {
                        return left;
                    }
                    else {
//                        currNode = left;
                        if(prevLeft) {
                            prevNode.left = left;
                        }
                        else {
                            prevNode.right = left;
                        }
                    }
                }
                else {
                    if(right.left == null) {
                        if(prevNode == null) {
                            return right;
                        }
                        right.left = currNode.left;
                        currNode = right;
                        if(prevLeft) {
                            prevNode.left = right;
                        }
                        else {
                            prevNode.right = right;
                        }
                    }
                    else {
                        TreeNode smallest = right.left, smallestPrev = right;
                        while(smallest.left != null) {
                            smallestPrev = smallest;
                            smallest = smallest.left;
                        }
                        smallestPrev.left = smallest.right;
                        smallest.right = right;
                        smallest.left = left;
                        if(prevNode == null) {
                            return smallest;
                        }
                        if(prevLeft) {
                            prevNode.left = smallest;
                        }
                        else {
                            prevNode.right = smallest;
                        }
                    }
                }
                break;
            }

            prevNode = currNode;
            if(currNode.key < key) {
                currNode = currNode.right;
                prevLeft = false;
            }
            else {
                currNode = currNode.left;
                prevLeft = true;
            }
        }

        return root;
    }


    // BFS;; in Class 6
    List<List<Integer>> layerByLayer(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) {
            return result;
        }

        queue.offer(root);
        while(!queue.isEmpty()) {
            int layerSize = queue.size();
            List<Integer> layerKeys = new LinkedList<>();
            for(int i = 0; i < layerSize; i++) {
                TreeNode curr = queue.poll();
//                if(curr == null) {
//                    queue.poll();
//                    continue;
//                }
                layerKeys.add(curr.key);
                if(curr.left!= null) queue.offer(curr.left);
                if(curr.right!= null) queue.offer(curr.right);
            }
            result.add(layerKeys);
        }

        return result;
    }

    // BFS, to check if a tree is complete;; in Class 6
    boolean isComplete(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) return true;

        queue.offer(root);
        boolean ended = false;  // SHOULD BE OUTSIDE!!!!!!!
        while(!queue.isEmpty()) {
            int layerSize = queue.size();
//            boolean ended = false;
            for(int i = 0; i < layerSize; i++) {
                TreeNode curr = queue.poll();
                if(curr.right == null) {
                    if(curr.left != null) {
                        if(ended) return false;
                        queue.offer(curr.left);
                    }
                    ended = true;
                } else{
                    if(curr.left == null) {
                        return false;  // bubble here
                    }
                    else {
                        if(ended) return false;
                        queue.offer(curr.left);
                        queue.offer(curr.right);
                    }
                }
            }
        }

        return true;
    }
}