package ru.job4j.kiss;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        //return searchMinMax(value, comparator, true);
        return Collections.max(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        //return searchMinMax(value, comparator, false);
        return Collections.min(value, comparator);
    }

    public <T> T searchMinMax(List<T> value, Comparator<T> comparator, boolean max) {
//        Collections.
      T temp = null;
//        for (T t : value) {
//            if (temp == null) {
//                temp = t;
//            } else {
//                if (max) {
//                    temp = comparator.compare(t, temp) > 0 ? t : temp;
//                } else {
//                    temp = comparator.compare(t, temp) < 0 ? t : temp;
//                }
//            }
//        }
       return temp;
    }

    public static void main(String[] args) {
        var minMax = new MaxMin();
        System.out.println(minMax.max(List.of("mm", "mmmm", "k"), (o1, o2) -> o1.length() - o2.length()));
        System.out.println(minMax.min(List.of("mm", "mmmm", "k"), (o1, o2) -> o1.length() - o2.length()));
        System.out.println("h".intern());

    }
}
