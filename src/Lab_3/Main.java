package Lab_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary("src/Lab_3/Dictionary_entries.txt");
        System.out.println("Введите строку для перевода");

        try {
            String consoleString = readLineFromConsole();
            dictionary.translate(consoleString);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String readLineFromConsole() throws IOException
    {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        return buffReader.readLine();
    }
}
