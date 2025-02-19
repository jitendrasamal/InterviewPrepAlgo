package personal.practice.educativeio.customdatastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnapshotArrayTest {

    @Test
    void setAndGetInitialValue() {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        assertEquals(0, snapshotArray.get(0, 0));
    }

    @Test
    void setAndGetAfterSnap() {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0, 5);
        int snapId = snapshotArray.snap();
        assertEquals(5, snapshotArray.get(0, snapId));
    }

    @Test
    void getPreviousSnapValue() {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0, 5);
        int snapId1 = snapshotArray.snap();
        snapshotArray.set(0, 10);
        int snapId2 = snapshotArray.snap();
        assertEquals(5, snapshotArray.get(0, snapId1));
        assertEquals(10, snapshotArray.get(0, snapId2));
    }

    @Test
    void getNonExistentSnapValue() {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0, 5);
        int snapId = snapshotArray.snap();
        assertEquals(0, snapshotArray.get(1, snapId));
    }

    @Test
    void multipleSetsAndSnaps() {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0, 5);
        int snapId1 = snapshotArray.snap();
        snapshotArray.set(0, 10);
        int snapId2 = snapshotArray.snap();
        snapshotArray.set(0, 15);
        int snapId3 = snapshotArray.snap();
        assertEquals(5, snapshotArray.get(0, snapId1));
        assertEquals(10, snapshotArray.get(0, snapId2));
        assertEquals(15, snapshotArray.get(0, snapId3));
    }

    @Test
    void setAndGetMultipleIndices() {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0, 5);
        snapshotArray.set(1, 10);
        snapshotArray.set(2, 15);
        int snapId = snapshotArray.snap();
        assertEquals(5, snapshotArray.get(0, snapId));
        assertEquals(10, snapshotArray.get(1, snapId));
        assertEquals(15, snapshotArray.get(2, snapId));
    }
}