import lombok.ToString;

public class FailedGoogleInt {
    public static void main(String[] args) {
        System.out.println(longestDurationForAnyProfit(new int[] {1, 2, 3, 4, 5}));
        System.out.println(longestDurationForAnyProfit(new int[] {5, 4, 3, 2, 1}));
        System.out.println(longestDurationForAnyProfit(new int[] {5, 3, 1, 2, 4}));
    }

    public static Result longestDurationForAnyProfit(int[] prices) {
        int n = prices.length;

        int[] minpricetillnow = new int[n];
        minpricetillnow[0] = prices[0];
        for (int i = 1; i < n; i++) {
            minpricetillnow[i] = Math.min(minpricetillnow[i - 1], prices[i]);
        }

        int res = 0;
        Result result = new Result();
        for (int i = 1; i < n; i++) {
            int findFirstLower = findFloorBinarySearch(minpricetillnow, prices[i], 0, i - 1);
            if (findFirstLower < 0 || findFirstLower >= i) continue;
            if (res < i - findFirstLower) {
                res = i - findFirstLower;
                result.longestDuration = res;
                result.idxStart = findFirstLower;
                result.idxEnd = i;
                result.priceStart = prices[findFirstLower];
                result.priceEnd = prices[i];
            }
        }
        return result;
    }

    private static int findFloorBinarySearch(int[] minpricetillnow, int price, int lo, int hi) {
        int floor = hi + 1;
        if (lo > hi) return floor;
        int mid = lo + (hi - lo) / 2;
        while (lo <= hi) {
            if (minpricetillnow[mid] < price) {
                floor = mid;
                hi = mid - 1;
            } else if (minpricetillnow[mid] >= price) lo = mid + 1;
            mid = lo + (hi - lo) / 2;
        }
        return floor;
    }
}

@ToString
class Result {
    int longestDuration = 0, idxStart = -1, idxEnd = -1, priceStart = -1, priceEnd = -1;
}
