package personal.pratice.consistenthashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
    private final int numberOfVirtualNodes;
    // private static final String hashSalt = "ConsistentHashing#$@#";
    SortedMap<Integer, String> ring = new TreeMap<>();
    // private  static int hashSpace =  10^0;
    private MessageDigest md;

    ConsistentHashing(int numberOfVirtualNodes) throws NoSuchAlgorithmException {
        this.numberOfVirtualNodes = numberOfVirtualNodes;
        md = MessageDigest.getInstance("MD5");
    }
    public void addNode(String node) {
        for (int i = 0; i < numberOfVirtualNodes; i++) {
            ring.put(hash(node + i), node);
        }
    }
    public void removeNode(String node) {
        for (int i = 0; i < numberOfVirtualNodes; i++) {
            ring.remove(hash(node + i));
        }
    }
    public String getNode(String key) {
        if (ring.isEmpty()) {
            return null;
        }
        int hash = hash(key);
        if (ring.containsKey(hash)) {
            return ring.get(hash);
        }
        SortedMap<Integer, String> tailMap = ring.tailMap(hash);
        hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(hash);
    }

    private int hash(String key) {
        md.reset();
        md.update(key.getBytes());
        byte[] digest = md.digest();
        int hash = ((int) (digest[3] & 0x7F) << 24) |
                ((int) (digest[2] & 0xFF) << 16) |
                ((int) (digest[1] & 0xFF) << 8) |
                ((int) (digest[0] & 0xFF));
        return hash;
    }


    /*
    private int hash(String key) {
       //  return ((key + hashSalt).hashCode() & 0x7FFFFFFF) % hashSpace;
        return key.hashCode() & 0x7FFFFFFF;
    }
*/
    public void printRing(){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE / 2);
        System.out.println("printRing" + ring);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ConsistentHashing consistentHashing = new ConsistentHashing(100);
        consistentHashing.addNode("Node1");
        consistentHashing.addNode("Node2");
        consistentHashing.addNode("Node3");
        consistentHashing.printRing();

    }
}

