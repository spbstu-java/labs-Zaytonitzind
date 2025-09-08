package Lab_1;

import java.awt.*;
import java.util.Scanner;

interface Movement {
    void move(int distance);
}

class Walk implements Movement{
    public void move(int distance)
    {
        double time = (double) distance/6;
        System.out.printf("Идем пешком, расстояние %d км, время на дорогу %.2f часов\n", distance, time);
    }
}

class Fly implements Movement{
    public void move(int distance)
    {
        double time = (double) distance/390;
        System.out.printf("Летим как сапсан, расстояние %d км, время на дорогу %.2f часов\n", distance, time);
    }
}

class Ride implements Movement{
    public void move(int distance)
    {
        double time = (double) distance/71;
        System.out.printf("Едем на лошади, расстояние %d км, время на дорогу %.2f часов\n", distance, time);
    }
}
class Stand implements Movement{
    public void move(int distance)
    {
        System.out.println("Стоим на месте");
    }
}


class Hero{
    private  Movement movement;

    //В конструкторе сразу задаем базовую стратегию для избежания исключений
    public Hero(){
        this.chooseMovement(new Stand());
    }

    public void chooseMovement(Movement movement)
    {
        this.movement = movement;
    }

    public void move(int distance){
        movement.move(distance);
    }
}

class Game {
    static Hero hero = new Hero();
    static int distance = 0;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("1 - Выбрать метод перемещения");
            System.out.println("2 - Задать расстояние в км");
            System.out.println("3 - Вывести результат перемещения");
            System.out.println("0 - Выход");
            choice = readIntFromConsole(10);

            switch (choice)
            {
                case 1 -> ChangeMovement();
                case 2 -> ChangeDistance();
                case 3 -> hero.move(distance);
                case 0 -> System.out.println("Выход из приложения");
                default -> System.out.println("Неверный ввод");
            }
        } while (choice != 0);
    }

    public static void ChangeMovement(){
        int choice;
        System.out.println("1 - Идти пешком");
        System.out.println("2 - Ехать на лошади");
        System.out.println("3 - Лететь");
        System.out.println("Любое другое значение - стоять");
        choice = readIntFromConsole(10);
        switch (choice)
        {
            case 1 -> hero.chooseMovement(new Walk());
            case 2 -> hero.chooseMovement(new Ride());
            case 3 -> hero.chooseMovement(new Fly());
            default -> hero.chooseMovement(new Stand());
        }
    }

    public static void ChangeDistance(){
        System.out.println("Введите расстояние для преодоления");
        distance = readIntFromConsole(0);
    }

    public static int readIntFromConsole(int defaultValue) {
        Scanner s = new Scanner(System.in);
        System.out.print("Введите число: ");

        if (s.hasNextInt()) {
            return s.nextInt();
        } else {
            s.nextLine(); // очистить неверный ввод
            return defaultValue;
        }
    }
}