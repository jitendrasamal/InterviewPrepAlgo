package personal.practice.educativeio.customdatastructures;

//https://leetcode.com/problems/design-hashset/
//https://www.educative.io/courses/grokking-coding-interview/design-hashset

public class MyHashSet_TODO {
    //TODO
    public static void main(String[] args) {
        MyHashSet_TODO myHashSet = new MyHashSet_TODO();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // return True
        myHashSet.contains(3); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // return True
        myHashSet.remove(2);   // set = [1]
        myHashSet.contains(2); // return False, (already removed)
    }

    public MyHashSet_TODO() {

    }

    public void add(int key) {

    }

    public void remove(int key) {

    }

    public boolean contains(int key) {
        return false;
    }
}
