
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Raffle r = new Raffle();
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("\n\n\n");
            System.out.print("""
                                            menu:

                               1 - Добавить новую игрушку
                               2 - Просмотреть список игрушек
                               3 - Изменить частоту выиграша игрушки
                               4 - Провести розыгрыш и сохранить результат
                               0 - Выход

                                Выберите необходимый параметр >\s""");
            var selection = sc.next();
            System.out.println();
            switch (selection) {
                case "1" -> r.addToy();
                case "2" -> r.printToys();
                case "3" -> r.setFrequency();
                case "4" -> r.raffle();
                case "0" -> {
                    System.out.println();
                    System.out.println(" Выход из программы ");
                    System.out.println();
                    System.exit(0);
                }
                default -> System.out.println("Данная клавиша не используется, попробуйте еще раз.");
            }
        }
    }
}