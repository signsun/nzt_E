package week6;

public class week_6 {
    public static void main(String[] args) {
        int arr[] = {10, 5, 28, 39, 6, 4, 46, 72, 22, 50};
        System.out.println("--------冒泡排序--------");
        maopao(arr);
        System.out.println("--------插入排序--------");
        charu(arr);
        System.out.println("--------快速排序--------");
        kuaisu(arr,0,arr.length-1);
        for (int n = 0; n < arr.length; n++){
            if(n!=arr.length-1)
                System.out.print(arr[n]+",");
            else
                System.out.println(arr[n]);
        }
        System.out.println("--------合并排序--------");
        int []tmp=new int[arr.length];
        hebing(arr,0,arr.length-1,tmp);
        for (int n = 0; n < arr.length; n++){
            if(n!=arr.length-1)
                System.out.print(arr[n]+",");
            else
                System.out.println(arr[n]);
        }
        System.out.println("--------堆排序--------");
        Sort(arr,arr.length-1);
        for (int n = 0; n < arr.length; n++){
            if(n!=arr.length-1)
                System.out.print(arr[n]+",");
            else
                System.out.println(arr[n]);
        }

    }

    public static void maopao(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int n = 0; n < arr.length; n++){
            if(n!=arr.length-1)
                System.out.print(arr[n]+",");
            else
                System.out.println(arr[n]);
        }
    }

    public static void charu(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            int temp = arr[index];
            while (index - 1 >= 0 && arr[index - 1] > temp) {
                arr[index] = arr[index - 1];
                index--;
            }
            arr[index] = temp;
        }
        for (int n = 0; n < arr.length; n++){
            if(n!=arr.length-1)
                System.out.print(arr[n]+",");
            else
                System.out.println(arr[n]);
        }
    }

    public static void kuaisu(int arr[], int left, int right) {
        int i, j, temp, t;
        if (left < right)
            return;
        i = left;
        j = right;
        temp = arr[left];
        while (i < j) {
            while (temp <= arr[j] && i < j) {
                j--;
            }
            while (temp >= arr[i] && i < j) {
                i++;
            }
            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = temp;
        kuaisu(arr, left, j - 1);
        kuaisu(arr, j + 1, right);
    }

    public static void hebing1(int[] arr,int low,int mid,int high,int[] tmp){
        int i = 0;
        int j = low,k = mid+1;
        while(j <= mid && k <= high){
            if(arr[j] < arr[k]){
                tmp[i++] = arr[j++];
            }else{
                tmp[i++] = arr[k++];
            }
        }
        while(j <= mid){
            tmp[i++] = arr[j++];
        }
        while(k <= high){
            tmp[i++] = arr[k++];
        }
        for(int t=0;t<i;t++){
            arr[low+t] = tmp[t];
        }
    }
    public static void hebing(int[] arr,int low,int high,int[] tmp){
        if(low<high){
            int mid = (low+high)/2;
            hebing(arr,low,mid,tmp);
            hebing(arr,mid+1,high,tmp);
            hebing1(arr,low,mid,high,tmp);
        }
    }

    public static void MaxHeapify(int[] a,int index,int size){
        int l=2*index;
        int r=2*index+1;
        int largest=index;
        if(l<=size && a[l]>a[index]){
            largest=l;
        }
        if(r<=size && a[r]>a[largest]){
            largest=r;
        }
        if(largest!=index){
            int temp=a[largest];
            a[largest]=a[index];
            a[index]=temp;
            MaxHeapify(a,largest,size);
        }
    }
    public static void HeapBuild(int[] a,int size){
        for(int i=size/2;i>=1;i--){
            MaxHeapify(a,i,size);
        }
    }
    public static void Sort(int[] a,int size){
        HeapBuild(a,size);
        for(int i=size;i>=2;i--){
            int temp=a[i];
            a[i]=a[1];
            a[1]=temp;
            MaxHeapify(a,1,i-1);
        }
    }

}
