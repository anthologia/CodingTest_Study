package solutions.카카오.광고삽입.규연;

import java.util.*;
import java.util.stream.Collectors;

public class Sol_InsertAd {
    class Solution {

        public String solution(String play_time, String adv_time, String[] logs) {
            int play = timeToSec(play_time);
            int adv = timeToSec(adv_time);
            int[] pSum = new int[100 * 3600];
            for (String log : logs) {
                int start = timeToSec(log.substring(0, 8));
                int end = timeToSec(log.substring(9, 17));
                for (int i = start; i < end; i++) {
                    pSum[i]++;
                }
            }
            long sum = Arrays.stream(pSum).skip(adv).sum();
            long maxSum = sum;
            int maxIdx = 0;

            for (int i = adv; i < play; i++) {
                sum += pSum[i];
                sum -= pSum[i - adv];
                if (sum > maxSum) {
                    maxSum = sum;
                    maxIdx = i - adv + 1;
                }
            }


            return secToString(maxIdx);
        }

        int timeToSec(String time) {
            int[] temp = Arrays.stream(time.split(":"))
                    .mapToInt(Integer::parseInt).toArray();
            return temp[0] * 3600 + temp[1] * 60 + temp[2];
        }

        String secToString(int time) {
            int hour = time / 3600;
            time %= 3600;
            int min = time / 60;
            time %= 60;
            int sec = time;
            return intToString(hour) + ":" + intToString(min) + ":" + intToString(sec);
        }

        String intToString(int time) {
            if (time < 10) return "0" + time;
            return Integer.toString(time);
        }
    }
}


