import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class RadixSort {

    /**
     * Radix sort for data with identifier that ranges from 1-dataSize.
     *
     * @param data Data to be sorted
     * @param <E> Type of the data
     */
    public static <E extends RadixSortable> void sort(List<E> data) {
        final int maximumNumber = data.size();
        if(maximumNumber == 0) {
            return;
        }

        int numberOfDigits = (int) Math.log10(maximumNumber) + 1;
        int placeValue = 1;
        Class<? extends RadixSortable> dataType = data.get(0).getClass();
        //Since we don't want to clear and addAll to the List numberOfDigit times, we created a temporary array.
        final E[] dataArray = data.toArray((E[]) Array.newInstance(dataType, data.size()));
        //This is created outside of applyCountingSortOn and is used reused in order to safe memory and time.
        final E[] tempSortedValues = (E[]) Array.newInstance(dataType, data.size());
        while (numberOfDigits-- > 0) {
            applyCountingSortOn(dataArray, tempSortedValues, placeValue);
            placeValue *= 10;
        }
        data.clear();
        data.addAll(Arrays.asList(dataArray));
    }

    private static <E extends RadixSortable> void applyCountingSortOn(E[] data, E[] tempSortedValues, int placeValue) {
        final int range = 10; // radix or the base
        final int[] frequency = new int[range];
        final int length = data.length;

        for (int i = 0; i < length; i++) {
            int digit = (data[i].getRow() / placeValue) % range;
            frequency[digit]++;
        }

        for (int i = 1; i < range; i++) {
            frequency[i] += frequency[i - 1];
        }

        for (int i = length - 1; i >= 0; i--) {
            int digit = (data[i].getRow() / placeValue) % range;
            tempSortedValues[frequency[digit] - 1] = data[i];
            frequency[digit]--;
        }

        System.arraycopy(tempSortedValues, 0, data, 0, length);
    }
}
