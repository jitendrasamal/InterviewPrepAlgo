package interviews;

/*

Imagine you are given a stream of data points consisting of <timestamp, commodityPrice> you are supposed to return
the maxCommodityPrice at any point in time.
The timestamps in the stream can be out of order, or there can be duplicate timestamps, we need to update the
commodityPrice at that particular timestamp if an entry for the timestamp already exists.
Create an in-memory solution tailored to prioritize frequent reads and writes for the given problem statement.

(4, 27)
(6, 26)
(9, 25)
Get max price => output should be 27


 */

import java.util.*;

public class CommodityPrice {
    Map<Integer, Integer> timeToPrice = new HashMap<>();
    SortedMap<Integer, Set<Integer>> priceToTime = new TreeMap<>();
    Map<Integer, Integer> checkPointMaxValue = new HashMap<>();
    int maxCheckpoint = 0;

    public CommodityPrice() {

    }

    public static void main(String[] args) {
        CommodityPrice commodityPrice = new CommodityPrice();
        /*
            (4, 27)
            (6, 26)
            (9, 25)
         */
        System.out.println(commodityPrice.getMaxPrice()); // -1
        commodityPrice.add(4, 12); //<- checkpoint1
        commodityPrice.add(6, 25); //<- checkpoint2
        commodityPrice.add(9, 20); // <-checkpoint3
        commodityPrice.delete(6); // // <-checkpoint4
        System.out.println(commodityPrice.getMaxPriceV2(1)); // 12
        System.out.println(commodityPrice.getMaxPriceV2(2)); // 25
        System.out.println(commodityPrice.getMaxPriceV2(3)); // 25
        System.out.println(commodityPrice.getMaxPriceV2(4)); // 20
        /*
        System.out.println(commodityPrice.getMaxPrice()); // 25
        System.out.println(commodityPrice.getMaxPriceV2(1)); // 12
        System.out.println(commodityPrice.getMaxPriceV2(2)); // 25
        System.out.println(commodityPrice.getMaxPriceV2(3)); // 25
        commodityPrice.add(4, 20);
        System.out.println(commodityPrice.getMaxPrice()); // 25
        commodityPrice.add(1, 30);
        System.out.println(commodityPrice.getMaxPrice()); // 30
        commodityPrice.add(4, 270);
        commodityPrice.add(6, 260);
        commodityPrice.add(9, 250);
        commodityPrice.add(1, 300);
        System.out.println(commodityPrice.getMaxPrice()); // 300
        commodityPrice.delete(1);
        System.out.println(commodityPrice.getMaxPrice()); // 270
         */
    }

    public int getMaxPrice() {
        if (priceToTime.isEmpty()) return -1;
        return priceToTime.lastKey();
    }

    public void add(int time, int price) {
        deleteInternal(time, false);
        timeToPrice.put(time, price);
        Set<Integer> times = priceToTime.computeIfAbsent(price, x -> new HashSet<>());
        times.add(time);
        ++maxCheckpoint;
        checkPointMaxValue.put(maxCheckpoint, getMaxPrice());
    }

    public void delete(int time) {
        deleteInternal(time, true);
    }

    private void deleteInternal(int time, boolean updateCheckpoint) {
        if (!timeToPrice.containsKey(time)) {
            return;
        }
        int price = timeToPrice.get(time);
        Set<Integer> times = priceToTime.get(price);
        times.remove(time);
        if (times.isEmpty()) {
            priceToTime.remove(price);
        }
        timeToPrice.remove(time);

        if (updateCheckpoint) {
            ++maxCheckpoint;
            checkPointMaxValue.put(maxCheckpoint, getMaxPrice());
        }
    }

    public int getMaxPriceV2(int checkpoint) {
        if (!checkPointMaxValue.containsKey(checkpoint)) return -1;
        return checkPointMaxValue.get(checkpoint);
    }


}
