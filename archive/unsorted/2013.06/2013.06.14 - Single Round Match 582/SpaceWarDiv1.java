package tasks;

import java.util.Map;
import java.util.TreeMap;

public class SpaceWarDiv1 {
    public long minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength, long[] enemyCount) {
        int mxGirl = 0;
        int mxEnemy = 0;
        for (int i = 0; i < magicalGirlStrength.length; i++) {
            mxGirl = Math.max(mxGirl, magicalGirlStrength[i]);
        }

        for (int i = 0; i < enemyCount.length; i++) {
            mxEnemy = Math.max(mxEnemy, enemyStrength[i]);
        }

        if (mxEnemy > mxGirl)
            return -1;

        Map<Integer, Long> cnt = new TreeMap<Integer, Long>();

        for (int i = 0; i < enemyCount.length; i++) {
            if (!cnt.containsKey(-enemyStrength[i]))
                cnt.put(-enemyStrength[i], 0l);

            cnt.put(-enemyStrength[i], cnt.get(-enemyStrength[i]) + enemyCount[i]);
        }

        long[] f = new long[magicalGirlStrength.length];
        long mxF = 0;
        for (Map.Entry<Integer, Long> entry : cnt.entrySet()) {
            int strength = -entry.getKey();
            long count = entry.getValue();

            int mxCnt = 0;
            for (int i = 0; i < magicalGirlStrength.length; i++) {
                if (count > 0 && magicalGirlStrength[i] >= strength) {
                    long delta = Math.min(mxF - f[i], count);
                    count -= delta;
                    f[i] += delta;
                    if (f[i] == mxF)
                        mxCnt++;
                }
            }

            while (count > 0) {
                long forEach = count / mxCnt;
                if (forEach == 0)
                    ++forEach;


                for (int i = 0; i < magicalGirlStrength.length; i++) {
                    if (count > 0 && magicalGirlStrength[i] >= strength) {
                        if (f[i] == mxF) {
                            long delta = Math.min(count, forEach);
                            count -= delta;
                            f[i] += delta;
                        }
                    }
                }
                mxF += forEach;
            }
        }

        return mxF;
    }
}
