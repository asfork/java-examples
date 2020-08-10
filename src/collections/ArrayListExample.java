package collections;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.remove(0);

        Iterator<String> iteractor = list.iterator();
        while (iteractor.hasNext()) {
            System.out.println(iteractor.next());
        }
    }
}