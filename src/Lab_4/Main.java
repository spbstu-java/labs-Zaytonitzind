package Lab_4;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static Lab_4.StreamAPICalculations.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> first = Arrays.asList(1,2,3,4,5,6,7,8);
        System.out.println("Первый метод: " + getAvgFromInt(first));

        List<String> second = Arrays.asList("Old", "cat", "dog");
        System.out.println("Второй метод: " + stringListToUpperCase(second));

        List<Integer> third = Arrays.asList(1,1,2,3,4,5,2,6,7,7,9,8);
        System.out.println("Третий метод: " + getUniqueSquares(third));

        List<Integer> fourth = Arrays.asList(1,2,3,4,5,9);
        System.out.println("Четвертый метод: " + getLastElemFromCollection(fourth));

        List<Integer> fourth2 = Arrays.asList();
        try
        {
            System.out.println("Четвертый метод: " + getLastElemFromCollection(fourth2));
        }
        catch (Exception e)
        {
            System.out.println("Четвертый метод исключение: " + e.getMessage());
        }

        int[] arr1 = {1,2,3,4,5,6};
        int[] arr2 = {1,3,5,7,9};

        System.out.println("Пятый метод с целыми числами: " + getSumOfEven(arr1));
        System.out.println("Пятый метод без целых чисел: " + getSumOfEven(arr2));

        List<String> sixth = Arrays.asList("This","is","a","test","of","this","method");
        System.out.println("Шестой метод: " + stringListToMap(sixth));
    }
}
