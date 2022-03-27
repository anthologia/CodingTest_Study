package solutions.이분탐색.드래곤앤던전_16434.경현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class G4_16434{

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Integer.parseInt(st.nextToken());
        long MyATK = Integer.parseInt(st.nextToken());
        long CurHP  = 0;
        long MaxHp  = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            long T = Integer.parseInt(st.nextToken());
            long A = Integer.parseInt(st.nextToken());
            long H = Integer.parseInt(st.nextToken());

            if(T == 1){
                int count = (int) (H/MyATK);
                if(H % MyATK == 0) count--;
                CurHP += A * count;
                MaxHp = Math.max(MaxHp, CurHP);
            }else{
                MyATK += A;
                CurHP = Math.max(CurHP - H, 0);
            }
        }
        System.out.println(MaxHp+1);
    }
}