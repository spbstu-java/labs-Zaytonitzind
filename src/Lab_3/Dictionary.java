package Lab_3;

import Lab_3.CustomExceptions.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary {

    private static class Pair {
        public final String str;
        public int i;

        public Pair(String str, int i) {
            this.str = str;
            this.i = i;
        }
    }

    private final HashMap<String, String> dictionary;

    Dictionary() {
        dictionary = new HashMap<>();
    }

    Dictionary(String filename) {
        dictionary = new HashMap<>();
        getDictionaryEntries(filename);
    }

    public void getDictionaryEntries(String filename) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while (line != null) {
                if (!line.contains(" | ")) {
                    throw new InvalidFileFormatException(
                            "Неверный формат строки: " + line
                    );
                }

                dictionary.put(line.substring(0, line.indexOf(" | ")),
                        line.substring(line.indexOf(" | ") + 3));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(new FileReadException(
                    "Файл не найден: " + filename, e
            ));
        } catch (IOException e) {
            throw new RuntimeException(new FileReadException(
                    "Ошибка чтения файла: " + filename, e
            ));
        } catch (InvalidFileFormatException e) {
            throw new RuntimeException(e);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void translate(String base) {
        Pattern pattern = Pattern.compile("[\\p{L}\\p{Nd}]+|[^\\p{L}\\p{Nd}]");
        Matcher matcher = pattern.matcher(base);

        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tokens.size(); ) {
            Pair tmp = searchForLongestCandidate(tokens, i);

            if (tmp != null) {
                result.append(tmp.str);
                if (tmp.i != i)
                    i = tmp.i;
            } else {
                result.append(tokens.get(i));
            }


            for (int pos = i + 1; pos < tokens.size(); pos++) {
                String tmpStr = tokens.get(pos);
                if (tmpStr.matches("[^\\p{L}\\p{Nd}]")) {
                    result.append(tokens.get(pos));
                    i = pos;
                } else {
                    break;
                }
            }
            i++;
        }
        System.out.println(result);
    }

    private Pair searchForLongestCandidate(List<String> token, int i) {
        String longestMatch = null;
        int matchPos = 0;
        StringBuilder sb = new StringBuilder();

        for (int j = i; j < token.size(); j++) {
            String tmpToken = token.get(j).toLowerCase();
            if (tmpToken.matches("[\\p{L}\\p{Nd}]+")) {
                if (!sb.isEmpty()) sb.append(" ");
                sb.append(tmpToken);
                String candidate = sb.toString();
                if (dictionary.containsKey(candidate)) {
                    longestMatch = candidate;
                    matchPos = j;
                }
            }
        }
        if (longestMatch != null) {
            return new Pair(dictionary.get(longestMatch), matchPos);
        }
        return null;
    }

}
