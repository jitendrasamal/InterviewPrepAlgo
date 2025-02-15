package personal.pratice;

import java.util.Map;
import java.util.TreeMap;

/*
https://leetcode.com/discuss/interview-question/5994275/Atalassian-or-SDE2-or-Reject/
given a list of timestamps and commodity prices, find out highest commodity price at given timestamp. timestamps are not necessarily in sorted order, there can be multiple entries for a timestamp as well.
follow up - after each timestamp, commodity price entry, we are putting a checkpoint, given a timestamp and checkpoint find maximum commodity prices till then.
 */
public class CommodityPriceTracker {
    TreeMap<Long, Double> maxCommodityPriceByTimeStamp = new TreeMap<>();
    TreeMap<Long, Double> maxCheckpointPriceByTimeStamp = new TreeMap<>();
    Double maxGlobalCommodityPrice = -1.0;

    public static void main(String[] args) {
        CommodityPriceTracker tracker = new CommodityPriceTracker();
        tracker.addPrice(5L, 100.0);
        tracker.addPrice(3L, 90.0);
        System.out.println("Max price till checkpoint 4: " + tracker.getMaxPriceTillCheckpoint(4L)); // 90
        System.out.println("Max global price: " + tracker.getMaxGlobalPrice()); // 100

        tracker.addPrice(7L, 110.0);
        tracker.addPrice(5L, 120.0);
        tracker.addPrice(10L, 130.0);

        System.out.println("Max price at timestamp 5: " + tracker.getMaxPriceAtTimestamp(5L)); // 120
        System.out.println("Max price at timestamp 3: " + tracker.getMaxPriceAtTimestamp(3L)); // 90
        System.out.println("Max price at timestamp 7: " + tracker.getMaxPriceAtTimestamp(7L)); // 110
        System.out.println("Max price till checkpoint 6: " + tracker.getMaxPriceTillCheckpoint(6L)); // 120
        System.out.println("Max price till checkpoint 9: " + tracker.getMaxPriceTillCheckpoint(9L)); // 120 -- why wrong? wht is the fix?
        System.out.println("Max price till checkpoint 10: " + tracker.getMaxPriceTillCheckpoint(10L)); // 130
    }

    public void addPrice(Long timeStamp, Double price) {
        double newPrice = Math.max(maxCommodityPriceByTimeStamp.getOrDefault(timeStamp, Double.MIN_VALUE), price);
        maxCommodityPriceByTimeStamp.put(timeStamp, newPrice);
        //maxCheckpointPriceByTimeStamp.put(timeStamp, maxGlobalCommodityPrice);

        maxGlobalCommodityPrice = Math.max(maxGlobalCommodityPrice, newPrice);
        Map.Entry<Long, Double> floor = maxCheckpointPriceByTimeStamp.floorEntry(timeStamp);
        Double maxValueByTime = Math.max(floor == null ? -1.0 : floor.getValue(), newPrice);

        while (maxCheckpointPriceByTimeStamp.ceilingEntry(timeStamp) != null) {
            timeStamp = maxCheckpointPriceByTimeStamp.ceilingKey(timeStamp);
            if (maxCheckpointPriceByTimeStamp.get(timeStamp) < maxValueByTime)
                maxCheckpointPriceByTimeStamp.put(timeStamp, maxValueByTime);
            else break;
        }
        maxCheckpointPriceByTimeStamp.put(timeStamp, maxValueByTime);


    }

    public Double getMaxPriceAtTimestamp(Long checkpoint) {
        return maxCheckpointPriceByTimeStamp.getOrDefault(checkpoint, -1.0);
    }

    public Double getMaxPriceTillCheckpoint(Long timestamp) {
        Map.Entry<Long, Double> floor = maxCommodityPriceByTimeStamp.floorEntry(timestamp);
        return floor == null ? -1.0 : floor.getValue();
    }

    public Double getMaxGlobalPrice() {
        return maxGlobalCommodityPrice;
    }
}
