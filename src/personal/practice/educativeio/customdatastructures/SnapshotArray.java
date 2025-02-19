package personal.practice.educativeio.customdatastructures;

import java.util.TreeMap;

//https://www.educative.io/courses/grokking-coding-interview/snapshot-array
//https://leetcode.com/problems/snapshot-array/description/

class SnapshotArray {
    int snapId = 0;
    TreeMap<Integer, Integer>[] historyRecords;

    public SnapshotArray(int length) {
        historyRecords = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            historyRecords[i] = new TreeMap<>();
            historyRecords[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        TreeMap<Integer, Integer> historyRecord = historyRecords[index];
        historyRecord.put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> historyRecord = historyRecords[index];
        return historyRecord.floorEntry(snap_id).getValue();
    }
}
