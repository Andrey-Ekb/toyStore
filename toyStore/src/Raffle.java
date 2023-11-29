import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Raffle {

    private static ArrayList<Toy> toys = new ArrayList<>();
    private static PriorityQueue<Toy> prizes = new PriorityQueue<>();

    private static int idCounter = 0;

    public void addToy() {
        Scanner scan = new Scanner(System.in);
        String title;
        int frequency;
        while (true) {
            System.out.print("Ведите название игрушки: ");
            title = scan.nextLine();
            if (title.isEmpty()) {
                System.out.println("Некорректный ввод, попробуйте еще раз ...");
                break;
            }
            System.out.print("Введите частоту выиграша: ");
            String value = scan.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0) {
                    System.out.println("Некорректный ввод, попробуйте еще раз ...");
                } else {
                    Toy toy = new Toy(idCounter, title, frequency);
                    if (!toys.contains(toy) || toys.size() == 0) {
                        idCounter++;
                        toys.add(toy);
                        System.out.println();
                        System.out.println("Добавлена новая игрушка");
                    } else {
                        System.out.println();
                        System.out.println("Такая игрушка уже есть в призовом фонде.");
                    }
                }
            } else {
                System.out.println("Некорректный ввод, попробуйте еще раз...");
            }
            break;
        }
    }

    public void  printToys(){
        for (int i = 0; i < toys.size(); i ++){
            System.out.println(toys.get(i));
        }
    }

    public void setFrequency() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ID игрушки: ");
        String value = scan.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId >= 0 && selectedId < toys.size()) {
                System.out.println("Игрушка " + toys.get(selectedId).getToyTitle() +
                     " имеет частоту выигрыша  "+ toys.get(selectedId).getToyVictoryFrequency());
                System.out.print("Введите новый показатель выиграшей: ");
                value = scan.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    toys.get(selectedId).setToyVictoryFrequency(newFrequency);
                    System.out.println();
                    System.out.println("Частота изменена. ");
                } else {
                    System.out.println("Некорректный ввод, попробуйте еще раз...");
                }
            } else {
                System.out.println("В призовом фонде нет игрушки с таким Id.");
            }
        } else {
            System.out.println("Некорректный ввод, попробуйте еще раз...");
        }
    }


    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Toy getPrize() {
        if (prizes.size() == 0) {
            Random rnd = new Random();
            for (Toy toy : toys) {
                for (int i = 0; i < toy.getToyVictoryFrequency(); i++) {
                    Toy temp = new Toy(toy.getToyId(), toy.getToyTitle(), rnd.nextInt(1, 10));
                    prizes.add(temp);
                }
            }
        }
        return prizes.poll();
    }

    public void raffle() {
        if (toys.size() >= 2) {
            Toy prize = getPrize();
            System.out.println("Приз :  " + prize.getToyTitle());
            saveResult(prize.getInfo());
        } else {
            System.out.println("В призовом фонде необходимо иметь не менее 2 игрушек, добавте ... ");
        }
    }

    private void saveResult(String text) {
        File file = new File("Result.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter("Result.txt", true)) {
            fw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}