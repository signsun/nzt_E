package week8;
import java.util.*;

public class week88 {
    static Random random=new Random();
    public static void test(){
        System.out.println(task1("abbc","dog cat cat fish"));
        System.out.println(task2(new int[]{1,2,2,3,0}));
        for(int i=0;i<5;i++){
            int target = random.nextInt(15) - 3;
            System.out.println("target:"+target+"\tresult:"+task3(new int[] {0,4,5,6,8},target));
        }
    }
    public static void main(String[] args) {
        test();
    }
    public static boolean task1(String pattern, String str) {
        //第一题代码
        String[] strings = str.split(" ");
        if(strings.length!=pattern.length())
            return false;
        Map<Character, List<Integer>> map=new HashMap<>();
        Map<String, List<Integer>> map2=new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if(map2.get(strings[i])!=null)
            {
                List<Integer> list = map2.get(strings[i]);
                list.add(i);
                map2.put(strings[i],list);
            }
            else
            {
                List<Integer> list=new ArrayList<>();
                list.add(i);
                map2.put(strings[i],list);
            }
            if(map.get(c)!=null)
            {
                List<Integer> list = map.get(c);
                list.add(i);
                map.put(c,list);
                if(map.get(c).equals(map2.get(strings[i]))==false)
                    return false;
            }
            else
            {
                List<Integer> list=new ArrayList<>();
                list.add(i);
                map.put(c,list);
                if(map.get(c).containsAll(map2.get(strings[i]))==false)
                    return false;
            }
        }
        return true;
    }
    public static int task2(int[] nums) {
        //第二题代码
        int[] arr = new int[nums.length];
        int i = 0;
        for (i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
            if (arr[nums[i]] > 1) {
                return nums[i];
            }
        }
        return nums[i];
    }

    public static int task3(int []nums,int target){
        //第三题代码
        int low=0,high=nums.length-1,mid=0;
        while(low<=high){
            mid=(low+high)/2;
            if(target==nums[mid])
                return mid;
            if(target<nums[mid])
                high=mid-1;
            else
                low=mid+1;
        }
        return 0-mid-1;
    }

}
