package personal.practice.educativeio.customdatastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {

    HashMap<String, TreeMap<Integer, String>> keyVsTimeSeriesValues;

    public TimeMap() {
        keyVsTimeSeriesValues = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> sortedMap = keyVsTimeSeriesValues.computeIfAbsent(key, k -> new TreeMap<>());
        sortedMap.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!keyVsTimeSeriesValues.containsKey(key))
            return "";
        Map.Entry<Integer, String> floorEntry = keyVsTimeSeriesValues.get(key).floorEntry(timestamp);
        if (floorEntry == null) {
            return "";
        }
        return floorEntry.getValue();
    }
}

