package Lab_2;


import Lab_2.annotations.Repeater;
import Lab_2.figures.Square;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
//import java.util.Arrays;
//import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Square a = new Square(5,0,0);
        Square b = new Square(3, 3, 1);

        try {
            getRepeatedMethods(a, b);
        } catch (Exception e) {
            System.err.println("Ошибка в методах: " + e.getMessage());
            e.printStackTrace();
        }

    }


    public static void getRepeatedMethods(Square a, Square b) throws Exception
    {
        //Получаем все методы класса квадрат
        Method[] methods = Square.class.getDeclaredMethods();
        //Поскольку методы мы получаем в не фиксированном порядке, в рантайме могут получаться разные исходы выполнения
        //Pr: метод DisplaceXPosBySide может вызываться раньше чем PrintCoordinates и тогда мы получим отличающиеся от заданных изначально координат в выводе
        //либо DisplaceXPosBySide отработает позже и координаты в консоли будут такими же как были заданны
        //Для избежания не специфицированного поведения можно использовать сортировку порядка
        //Arrays.sort(methods, Comparator.comparing(Method::getName));

        for(Method method : methods)
        {
            //Проверяем есть ли у метода аннотация
            if(method.isAnnotationPresent(Repeater.class)) {
                int modifiers = method.getModifiers();

                //Проверяем является ли метод private/protected
                if (Modifier.isPrivate(modifiers) || Modifier.isProtected(modifiers)) {

                    Repeater repeat = method.getAnnotation(Repeater.class);
                    //Делаем приватные и защищенные методы доступными
                    method.setAccessible(true);

                    for (int i = 0; i < repeat.times(); i++) {
                        //Проверяем есть ли у метода параметры
                        if (method.getParameterCount() == 0) {
                            method.invoke(a); // методы без параметров
                        } else {
                            //Получаем количество параметров методов
                            Object[] argsForMethod = new Object[method.getParameterCount()];
                            for (int j = 0; j < argsForMethod.length; j++) {

                                Class<?> paramType = method.getParameterTypes()[j];
                                // Проверяем является ли параметр метода типа Square
                                if (paramType == Square.class) {
                                    argsForMethod[j] = b;
                                } else {
                                    argsForMethod[j] = null;
                                }
                            }

                            method.invoke(a, argsForMethod); // методы с параметрами
                        }
                    }
                }
            }
        }
    }
}
