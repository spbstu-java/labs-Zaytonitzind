package Lab_4;

import java.util.*;
import java.util.stream.*;

public class StreamAPICalculations {
    public static double getAvgFromInt(List<Integer> list)
    {
        return list.stream().mapToInt(Integer::intValue)
                .average().orElseThrow(() -> new NoSuchElementException("Список пуст"));
    }

    public static List<String> stringListToUpperCase(List<String> list)
    {
        return list.stream().map(x->"_new_"+x.toUpperCase()).toList();
    }

    public static List<Integer> getUniqueSquares(List<Integer> list)
    {
        return list.stream()
                .filter(x -> Collections.frequency(list, x) == 1)
                .map(x->x*x).toList();
    }

    public static <T> T getLastElemFromCollection(Collection<T> collection)
    {
        return collection.stream().reduce((first, second) -> second)
                .orElseThrow(()-> new NoSuchElementException("Коллекция пуста"));
    }

    public static int getSumOfEven(int[] arr)
    {
        return Arrays.stream(arr)
                .filter(x->x%2 == 0)
                .sum();
    }

    public static Map<Character, String> stringListToMap(List<String> list)
    {
        return list.stream().collect(Collectors.toMap(
                s->s.charAt(0),
                s->s.substring(1),
                (v1, v2) ->v1 //В случае одинаковых ключей берем первый
        ));
    }
}
