package solutions.카카오.광고삽입.주영;

public class Solution {
    static int[] timeLine = new int[360000];
    public static String solution(String play_time, String adv_time, String[] logs) {
        int playTimeSec = timeToSec(play_time);
        int advTimeSec = timeToSec(adv_time);

        for (String log : logs) {
            String[] secs = log.split("-");
            increaseTime(timeToSec(secs[0]), timeToSec(secs[1]));
        }

        long sum = 0;
        for (int i = 0; i < advTimeSec; i++) {
            sum += timeLine[i];
        }

        long maxSum = sum;
        int maxSumIdx = 0;

        for (int i = advTimeSec; i < playTimeSec; i++) {
            sum += timeLine[i] - timeLine[i - advTimeSec];
            if (sum > maxSum) {
                maxSum = sum;
                maxSumIdx = i - advTimeSec + 1;
            }
        }

        return secToTime(maxSumIdx);
    }

    public static void increaseTime(int start, int end) {
        for (int i = start; i < end; i++) {
            timeLine[i]++;
        }
    }

    public static int timeToSec(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 3600 + Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);
    }

    public static String secToTime(int sec) {
        String time = "";
        int h = sec / 3600;
        time += h < 10 ? "0" + h  + ":" : h + ":";
        sec %= 3600;

        int m = sec / 60;
        time += m < 10 ? "0" + m + ":" : m + ":";
        sec %= 60;

        int s = sec % 60;
        time += s < 10 ? "0" + s : s;

        return time;
    }
}
