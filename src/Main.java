import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static final List<RadixSortable> baseData = generateData();

    public static void main(String[] args) {
        List<RadixSortable> a = new ArrayList<>(baseData);
        List<RadixSortable> b = new ArrayList<>(baseData);

        long startB = System.nanoTime();
        RadixSort.sort(b);
        long endB = System.nanoTime();

        long startA = System.nanoTime();
        a.sort((o1, o2) -> o1.getRow().compareTo(o2.getRow()));
        long endA = System.nanoTime();

        System.out.println("Order is same: " + a.equals(b) + " (" + a.get(0).getRow() + "/" + b.get(0).getRow() + ")");
        System.out.println("Java : " + (endA - startA));
        System.out.println("Radix: " + (endB - startB));
        System.out.println("Radix % faster: " + (double) (endB - startB) / (double) (endA - startB) * 100);
    }

    private static List<RadixSortable> generateData() {
        final int size = 30000;
        List<RadixSortable> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add(new RadixSortable(i + 1));
        }
        Collections.shuffle(data);
        return data;
    }
}
