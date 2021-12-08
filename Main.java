package week5;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("-----------第一题------------");//数字反转
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        String s=reverse(str);
        int sum=0;
        for(int i=0;i<s.length();i++){
            sum+=(s.charAt(i)-'0')*Math.pow(10,s.length()-i-1);
        }
        System.out.println(sum);
        System.out.println("-----------第二题------------");
        int n=sc.nextInt();
        int[] arr=new int[n+1];
        arr[0]=1;
        arr[1]=1;
        for (int i=2;i<n+1;i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
        System.out.println(arr[n]);
        System.out.println("-----------第三题------------");//求子集
        int []num={1,2,3};
        ArrayList<ArrayList<Integer>> list=null;
        list=getSub(num);
        System.out.print("[");
        for(int i=0;i<list.size();i++){
            ArrayList<Integer> m=list.get(i);
            if(i!=list.size()-1)
                System.out.print(m+",");
            else
                System.out.print(m);
        }
        System.out.print("]");
    }
    public static  String reverse(String s){
        String str="";
        for(int i=0;i<s.length();i++){
            str=s.charAt(i)+str;
        }
        return str;
    }
    static ArrayList<ArrayList<Integer>> getSub(int args[])
    {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> subList = null;
        int max = 1 << args.length;

        for (int i = 0; i < max; i++)
        {
            subList = new ArrayList<Integer>();

            int k = i;
            int index = 0;
            while (k > 0)
            {
                if ((k & 1) > 0)
                {
                    subList.add(args[index]);
                }
                k >>= 1;
                index++;
            }
            result.add(subList);
        }
        return result;
    }

}
