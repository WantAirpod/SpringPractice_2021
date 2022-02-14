import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 2 1
1 1 1 1 1
 */
public class 백준_2210_숫자판점프 {


    private static int[][] arr;
    private static String[] str;
    private static int c;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static HashSet<String> set;
    private static String numStr;
    private static void dfs(int x, int y , int cnt, String s){
        if(cnt==6){
            set.add(s);
            return;
        }


        for(int w= 0; w<4 ;w++){
            int nx = dx[w] + x;
            int ny = dy[w] + y;
            if(nx<0||ny<0||nx>=5||ny>=5)
                continue;
            dfs(nx,ny,cnt+1,s+arr[x][y]);

        }

    }


    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[5][5];
        set =new HashSet<>();
        dx = new int[4];
        dy = new int[4];

        for (int i = 0; i < 5; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }
        numStr = new String();
        for(int i =0 ; i <5 ; i++){
            for(int j = 0 ; j < 5; j++){
                dfs(i,j,0,numStr);
            }
        }

        System.out.println(set.size());

    }
}
