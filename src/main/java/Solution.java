import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

class Solution {

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        if (m == n) {
            long mm = m - 1;
            return (int) (mm * mm % 1000000007);
        }
        int[] hDistances = everDistancesPossible(m, hFences);
        int[] vDistances = everDistancesPossible(n, vFences);
        int hIdx = hDistances.length - 1;
        int vIdx = vDistances.length - 1;
        while (hIdx >= 0 && vIdx >= 0) {
            int d = hDistances[hIdx] - vDistances[vIdx];
            if (d > 0) {
                hIdx--;
            } else if (d < 0) {
                vIdx--;
            } else {
                long mm = hDistances[hIdx];
                return (int) (mm * mm % 1000000007);
            }
        }
        return -1;
    }


    private static int[] everDistancesPossible(int n, int[] fences) {
        if (fences.length == 0) {
            return new int[]{n - 1};
        }

        java.util.Arrays.sort(fences);
        Set<Integer> distances = new HashSet<>();
        distances.add(n - 1);
        for (int i = 0; i < fences.length; i++) {
            distances.add(fences[i] - 1);
            distances.add(n - fences[i]);
            for (int j = i + 1; j < fences.length; j++) {
                distances.add(fences[j] - fences[i]);
            }
        }
        int[] distancesArray = distances.stream().mapToInt(t -> t).toArray();
        java.util.Arrays.sort(distancesArray);
        return distancesArray;
    }
}