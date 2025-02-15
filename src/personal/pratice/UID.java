package personal.pratice;

import java.util.UUID;

public class UID {
    public static void main(String[] args) {
        String input = "jitendra@example.com";
        System.out.println("UUID from String: " + UUID.nameUUIDFromBytes(input.getBytes()));
        System.out.println("UUID from String: " + UUID.nameUUIDFromBytes(input.getBytes()));
        System.out.println("UUID from String: " + UUID.nameUUIDFromBytes(input.getBytes()));
        System.out.println("UUID from String: " + UUID.nameUUIDFromBytes(input.getBytes()));
        System.out.println("UUID from String: " + UUID.nameUUIDFromBytes(input.getBytes()));
        System.out.println("UUID from String: " + UUID.nameUUIDFromBytes(input.getBytes()));
        System.out.println("UUID from String: " + UUID.nameUUIDFromBytes(input.getBytes()));

        // System.out.println("UUID from time: " + UlidCreator.getTimeUUID());
        // System.out.println("UUID from time: " + UlidCreator.getTimeUUID());

    }
}
