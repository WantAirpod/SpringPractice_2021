public class Main {
    public static void main(String args[]){
        int arr[] = {0,0,0,0,0,0,1};
        int cnt = 0;
        int maxNum = -987654321;
        boolean flag = false;
        for(int i = 0 ; i < arr.length-2; i++){
            if(arr[i] == arr[i+2]){
                flag =true;
                cnt++;
                if(maxNum < cnt) maxNum = cnt;
            }else{
                cnt=0;
            }
        }
        if(arr.length==1) {
            System.out.println(1);
        }
        else if(flag==false)
            System.out.println(0);
        else
            System.out.println(maxNum+2);
    }
}
