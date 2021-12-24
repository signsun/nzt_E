package week7;

import jdk.nashorn.internal.IntDeque;

import java.util.LinkedList;
import java.util.Stack;

public class week7 {


    public static LinkedList<Integer> list = new LinkedList<>();
    public static void main(String[] args) {
        int []num1={2,4};
        int []num2={1,2,4,3};
        int[] arr = nextarr(num1, num2);
        for(int i=0;i<arr.length;i++){
            if (i != arr.length - 1) {
                System.out.print(arr[i]+",");
            }
            else
                System.out.println(arr[i]);
        }
        int[] pushed = {1,2,3,4,5};
        int[] poped = {4,3,5,1,2};
        System.out.println(bol(pushed,poped));
        int []arr2={1,2,3,4,4};
        System.out.println(sum(arr2));
    }
    public static int[] nextarr(int[] nums1, int[] nums2) {
        Stack<Integer> myStack1 = new Stack<>();
        Stack<Integer> myStack2 = new Stack<>();
        int usedsize = 0;
        int[] arr = new int[nums1.length];
        for(int i = nums2.length - 1;i >= 0;i--){
            myStack1.push(nums2[i]);
        }
        for(int j = 0;j < nums1.length;j++){
            int temp = nums1[j];
            while(!myStack1.empty() && temp != myStack1.peek()){
                myStack2.push(myStack1.peek());
                myStack1.pop();
            }

            while(!myStack1.empty()){
                if(temp < myStack1.peek()){
                    arr[usedsize++] = myStack1.peek();
                    break;
                }
                myStack2.push(myStack1.peek());
                myStack1.pop();
            }
            if(myStack1.empty()){
                arr[usedsize++] = -1;
            }
            while(!myStack2.empty()){
                myStack1.push(myStack2.peek());
                myStack2.pop();
            }
        }
        return arr;
    }
    public static boolean bol(int[] pushed, int[] popped) {
        int length = pushed.length;
        int j = 0;
        for (int i = 0; i < length; i++) {
            list.push(pushed[i]);
            while (list.peek()!=null&&(list.peek() == popped[j])){
                j++;
                list.pop();
            }
        }
        if (j!=length){
            return false;
        }
        return true;
    }
    public static int sum(int[] nums) {
        int[] arr = new int[101];
        for (int num : nums) {
            arr[num]++;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (arr[nums[i]] == 1) {
                sum += nums[i];
            }
        }
        return sum;
    }

}
