package applications;

import net.datastructures.BinaryTree;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

public class Lab13Sum {
    private static void check(int expected, int actual) throws Exception {
       if (expected != actual) throw new Exception("expected="+expected+" actual="+actual);
    }

    private static <E> void check(Position<E> expected, Position<E> actual) throws Exception {
        if (expected != actual) throw new Exception("expected="+expected+" actual="+actual);
    }

    public static void main(String[] args) throws Exception {

        //Testing sumOfLeaves
        {
            LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();

            // create this tree incrementally
            //       10
            //      /   \
            //     20   30
            //    /     / \
            //  40     60  70
            //              \
            //               80
            Position<Integer> myRoot = lbt.addRoot(10);
            check(10, sumOfLeaves(lbt));

            Position<Integer> L = lbt.addLeft(myRoot, 20);
            check(20, sumOfLeaves(lbt));

            Position<Integer> R = lbt.addRight(myRoot, 30);
            check(50, sumOfLeaves(lbt));

            Position<Integer> RR = lbt.addRight(R, 70);
            check(90, sumOfLeaves(lbt));

            lbt.addLeft(R, 60);
            check(150, sumOfLeaves(lbt));

            lbt.addRight(RR, 80);
            check(160, sumOfLeaves(lbt));

            lbt.addLeft(L, 40);
            check(180, sumOfLeaves(lbt));

            System.out.println("Passed all tests for Problem 1");
        }

        {
            LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();

            // create this tree incrementally
            //       10
            //      /   \
            //     0     30
            //    / \   / \
            //  25  35 20  40
            // /
            // 70
            Position<Integer> myRoot = lbt.addRoot(10);
            check(myRoot, maximumPosition(lbt));

            Position<Integer> L = lbt.addLeft(myRoot, 0);
            check(myRoot, maximumPosition(lbt));

            Position<Integer> R = lbt.addRight(myRoot, 30);
            check(R, maximumPosition(lbt));

            Position<Integer> RR = lbt.addRight(R, 40);
            check(RR, maximumPosition(lbt));

            lbt.addLeft(R, 35);
            check(RR, maximumPosition(lbt));

            Position<Integer> LL = lbt.addLeft(L, 25);
            check(RR, maximumPosition(lbt));

            Position<Integer> LLL = lbt.addLeft(LL, 70);
            check(LLL, maximumPosition(lbt));

            System.out.println("Passed all tests for Problem 2");
        }
    }

    // An example of a recursive function on a BinaryTree
    // Return the sum of all the elements in tree
    public static int sum(BinaryTree<Integer> tree) {
        if (tree.isEmpty()) return 0;
        else return sumHelper(tree, tree.root());
    }

    // helper function for sum, where the actual recursion happens
    private static int sumHelper(BinaryTree<Integer> tree, Position<Integer> p) {
        if (p == null) {
            return 0;
        } else {
            int s = p.getElement();
            s += sumHelper(tree, tree.left(p));
            s += sumHelper(tree, tree.right(p));
            return s;
        }
    }

    // Problem 1
    // Return the sum of only the leaves of the tree
    // Requirement: use recursion
    // HINT: you'll need a helper function. Why?

    public static int sumOfLeaves(BinaryTree<Integer> tree) {
        if(tree.isEmpty()){
            return 0;
        }
        else{
            return sumOfLeavesHelper(tree, tree.root());
        }
    }
    public static int sumOfLeavesHelper(BinaryTree<Integer> tree, Position<Integer> p) {
        if (p == null){
            return 0;
        }
        else {
            int s = 0;
            s += sumOfLeavesHelper(tree, tree.left(p));
            s += sumOfLeavesHelper(tree, tree.right(p));
            if ( s == 0){
                return  p.getElement();
            }
            return s;
        }
    }
    // Problem 2
    // Return the Position which has the largest element of all Positions in the tree
    // Requirement: use recursion
    // HINT: you'll need a helper function. Why?
    public static Position<Integer> maximumPosition(BinaryTree<Integer> tree) {
        if(tree.isEmpty()){
            return null;
        }
        else{
            return maximumPositionHelper(tree, tree.root());
        }
    }
    public static Position<Integer> maximumPositionHelper(BinaryTree<Integer> tree, Position<Integer> p){
        if (p == null){
            return null;
        }
        else {
            Position<Integer> max1 = maximumPositionHelper(tree, tree.left(p));
            Position<Integer> max2 = maximumPositionHelper(tree, tree.right(p));
            if(max1 != null && max2 != null){
                if (p.getElement() < max1.getElement() && p.getElement() > max2.getElement()){
                    return max1;
                }
                if (p.getElement() < max2.getElement() && p.getElement() > max1.getElement()){
                    return max2;
                }
                if (p.getElement() < max1.getElement() && p.getElement() < max2.getElement()){
                    if (max1.getElement() > max2.getElement()){
                        return max1;
                    }
                    return max2;
                }
            }
            else if (max2 == null && max1 != null){
                if (max1.getElement() > p.getElement()){
                    return max1;
                }
            }
            else if (max1 == null && max2 != null){
                if ( max2.getElement() > p.getElement()){
                    return max2;
                }
            }
            return p;
        }
    }
}
