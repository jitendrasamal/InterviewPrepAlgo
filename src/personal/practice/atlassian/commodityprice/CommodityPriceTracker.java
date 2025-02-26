package personal.practice.atlassian.commodityprice;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

//https://leetcode.com/discuss/interview-question/5994275/Atalassian-or-SDE2-or-Reject/

/**
 * given a list of timestamps and commodity prices, find out highest commodity price at given timestamp. timestamps
 * are not necessarily in sorted order, there can be multiple entries for a timestamp as well.
 * follow up - after each timestamp, commodity price entry, we are putting a checkpoint, given a timestamp and
 * checkpoint find maximum commodity prices till then.
 */
public class CommodityPriceTracker {
    TreeMap<Long, Double> maxPriceByTimeStamp = new TreeMap<>();
    TreeMap<Long, Double> maxCheckpointPriceByTimeStamp = new TreeMap<>();
    Double maxGlobalCommodityPrice = -1.0;

    @SuppressWarnings("comment")
    public static void main(String[] args) {
        CommodityPriceTracker tracker = new CommodityPriceTracker();
        tracker.addPrice(2L, 110.0);
        tracker.addPrice(5L, 100.0);
        tracker.addPrice(3L, 90.0);
        System.out.println("Max price till checkpoint 5: " + tracker.getMaxPriceTillCheckpoint(5L)); // 110
        System.out.println("Max price till checkpoint 4: " + tracker.getMaxPriceTillCheckpoint(4L)); // 90
        System.out.println("Max global price: " + tracker.getMaxGlobalPrice()); // 100

        tracker.addPrice(7L, 110.0);
        tracker.addPrice(5L, 120.0);
        tracker.addPrice(5L, 120.0);
        tracker.addPrice(10L, 130.0);

        System.out.println("Max price at timestamp 5: " + tracker.getMaxPriceAtTimestamp(5L));
        System.out.println("Max price at timestamp 3: " + tracker.getMaxPriceAtTimestamp(3L));
        System.out.println("Max price at timestamp 7: " + tracker.getMaxPriceAtTimestamp(7L));
        System.out.println("Max price till checkpoint 6: " + tracker.getMaxPriceTillCheckpoint(6L));
        System.out.println("Max price till checkpoint 9: " + tracker.getMaxPriceTillCheckpoint(9L));
        // wrong? wht is the fix?
        System.out.println("Max price till checkpoint 10: " + tracker.getMaxPriceTillCheckpoint(10L));
        System.out.println("Max price till checkpoint 2: " + tracker.getMaxPriceTillCheckpoint(2L));

        /**
         * Max price till checkpoint 5: 110.0
         * Max price till checkpoint 4: 110.0
         * Max global price: 110.0
         * Max price at timestamp 5: 120.0
         * Max price at timestamp 3: 90.0
         * Max price at timestamp 7: 110.0
         * Max price till checkpoint 6: 120.0
         * Max price till checkpoint 9: 120.0
         * Max price till checkpoint 10: 130.0
         * Max price till checkpoint 2: 110.0
         */
    }

    public void addPrice(Long timeStamp, Double price) {
        double newPrice = Math.max(maxPriceByTimeStamp.getOrDefault(timeStamp, Double.MIN_VALUE), price);
        maxPriceByTimeStamp.put(timeStamp, newPrice);

        maxGlobalCommodityPrice = Math.max(maxGlobalCommodityPrice, newPrice);

        Map.Entry<Long, Double> floor = maxCheckpointPriceByTimeStamp.floorEntry(timeStamp);
        double currentMax = floor != null ? floor.getValue() : -1.00;
        Double maxValueByTime = Math.max(newPrice, currentMax);
        maxCheckpointPriceByTimeStamp.put(timeStamp, maxValueByTime);

        propagateMaxValueInCheckPoint(timeStamp, maxValueByTime);

    }

    public Double getMaxPriceTillCheckpoint(Long checkpointTime) {
        Map.Entry<Long, Double> floor = maxCheckpointPriceByTimeStamp.floorEntry(checkpointTime);
        return floor == null ? -1.0 : floor.getValue();
    }

    public Double getMaxGlobalPrice() {
        return maxGlobalCommodityPrice;
    }

    public Double getMaxPriceAtTimestamp(Long timeStamp) {
        return maxPriceByTimeStamp.getOrDefault(timeStamp, -1.0);
    }

    private void propagateMaxValueInCheckPoint(Long timeStamp, Double maxValue) {
        SortedMap<Long, Double> tailMap = maxCheckpointPriceByTimeStamp.tailMap(timeStamp, false);
        if (!tailMap.isEmpty()) {
            for (Long time : tailMap.keySet()) {
                Double value = maxCheckpointPriceByTimeStamp.get(time);
                if (value < maxValue) {
                    maxCheckpointPriceByTimeStamp.put(time, maxValue);
                } else {
                    break;
                }
            }
        }
    }
}
