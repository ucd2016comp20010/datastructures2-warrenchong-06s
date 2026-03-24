package project20280.exercises;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;

class Wk7LeetcodeSolutions {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> largest = new PriorityQueue<>();

        for (int i = 0;  i < k; i++){
            largest.add(nums[i]);
        }

        for (int j = k; j <  nums.length; j++){
            if (largest.peek() < nums[j]){
                largest.poll();
                largest.add(nums[j]);
            }
            //System.out.println(largest.toString());
        }

        return largest.peek();
    }


    public static void main(String[] args) {
        int[] ex1 = {3,2,1,5,6,4};
        int k1 = 2;

        int[] ex2 = {3,2,3,1,2,4,5,5,6};
        int k2 = 4;

        int[] ex3 = {-1,2,0};
        int k3 = 2;

        //int debug = findKthLargest(ex3,k3);

        System.out.println("The kth largest value for the array " + Arrays.toString(ex1) + " with k = " + k1 + " is " + findKthLargest(ex1, k1));
        System.out.println("The kth largest value for the array " + Arrays.toString(ex2) + " with k = " + k2 + " is " + findKthLargest(ex2, k2));
        System.out.println("The kth largest value for the array " + Arrays.toString(ex3) + " with k = " + k3 + " is " + findKthLargest(ex3, k3));
    }


}
