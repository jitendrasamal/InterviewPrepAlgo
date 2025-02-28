package interviews.digitalocean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
    /*

     r1 -> [start1, end1], [start2, end2] ....
     r2 -> [...]

    booking-> b_s b_e
              2, 3

               end_i <= b_s
               start_i+1 > b_e
     */
    Map<Integer, List<Booking>> bookings;

    public Hotel() {
        this.bookings = new HashMap<>();
        bookings.put(1, List.of(new Booking(1, 2), new Booking(4, 5)));
        bookings.put(2, List.of(new Booking(1, 3), new Booking(4, 5)));
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        System.out.println(hotel.findRoom(3, 3));
        System.out.println(hotel.findRoom(1, 3));
        System.out.println(hotel.findRoom(10, 11));

    }

    public List<Integer> findRoom(long start, long end) {
        List<Integer> rooms = new ArrayList<>();
        for (Map.Entry<Integer, List<Booking>> roomEntry : bookings.entrySet()) {
            if (isFree(start, end, roomEntry.getValue())) {
                rooms.add(roomEntry.getKey());
            }
        }
        return rooms;
    }

    private boolean isFree(long start, long end, List<Booking> bookingsEntries) {
        // assume roomSorted om enddate
        if (bookingsEntries.isEmpty()) {
            return true;
        }
        int index = 0;
        while (index < bookingsEntries.size() && bookingsEntries.get(index).end < start)
            index++;
        return index == bookingsEntries.size() || end < bookingsEntries.get(index).start;
    }

    private static class Booking {
        long start;
        long end;

        public Booking(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}
