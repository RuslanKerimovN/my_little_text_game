import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class ProgramLogic {
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) throws FileNotFoundException {
        int level;
        String fileName = "start.txt";
        ProgramLogic.ReadingFromFile(fileName);
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите уровень сложности (Легкий, Средний или Сложный): ");
        String str = sc.next();
        level = ProgramLogic.ChoosingTheDifficultyLevel(sc, str);
        System.out.print("Введите имя персонажа: ");
        String nameHero = sc.next();
        Hero hero = new Hero(nameHero);
        ZmeyGorinich zmey = new ZmeyGorinich(level);
        HashMap<String, Weapon> map = ProgramLogic.CreatingWeaponMap();
        System.out.println(ANSI_YELLOW + "Для выхода из игры введите ВЫХОД" + ANSI_RESET);
        while (true) {
            System.out.print("Выберите оружие для атаки (МЕЧ, ЛУК или КОПЬЕ), а РЕГЕН" + "\n" +
                    "используйте для восстановления здоровья: ");
            str = sc.next();
            if (!ProgramLogic.CommandRecognition(str, zmey, hero, map))
                return;
            if (zmey.getHealth() < 0)
                zmey.setHealth(0);
            System.out.println(ANSI_BLUE + "| Здоровье героя |" + " Здоровье Змея |");
            System.out.printf("%10d      %10d\n" + ANSI_RESET, hero.getHealth(), zmey.getHealth());
            if (zmey.getHealth() <= 0) {
                System.out.println("Змей повержен!!!!");
                fileName = "finish.txt";
                ProgramLogic.ReadingFromFile(fileName);
                break;
            }
            hero.setHealth(hero.getHealth() - zmey.hit(map, "ОГОНЬ"));
            if (hero.getHealth() < 0)
                hero.setHealth(0);
            System.out.println(ANSI_BLUE + "| Здоровье героя |" + " Здоровье Змея |");
            System.out.printf("%10d      %10d\n" + ANSI_RESET, hero.getHealth(), zmey.getHealth());
            if (hero.getHealth() <= 0) {
                System.out.println("Герой погиб!!!!");
                fileName = "finish2.txt";
                ProgramLogic.ReadingFromFile(fileName);
                break;
            }
        }

    }

    static int ChoosingTheDifficultyLevel(Scanner sc, String str) {
        int level = 0;
        Marker:
        while (true) {
            switch (str.toUpperCase()) {
                case "ЛЕГКИЙ":
                    level = 0;
                    break Marker;
                case "СРЕДНИЙ":
                    level = 1;
                    break Marker;
                case "СЛОЖНЫЙ":
                    level = 2;
                    break Marker;
                default:
                    System.out.print("Не расстраивай себя и игру: ");
                    str = sc.next();
                    break;
            }
        }
        return level;
    }

    static HashMap<String, Weapon> CreatingWeaponMap() {
        HashMap<String, Weapon> map = new HashMap<>();
        Weapon sward = new Weapon(25);
        Weapon bow = new Weapon(15);
        Weapon spear = new Weapon(20);
        Weapon dragonBall = new Weapon(20);
        map.put("МЕЧ", sward);
        map.put("ЛУК", bow);
        map.put("КОПЬЕ", spear);
        map.put("ОГОНЬ", dragonBall);
        return map;
    }

    static void ReadingFromFile(String fileName) throws FileNotFoundException {
        File file = new File(Objects.requireNonNull(ProgramLogic.class.
                getClassLoader().getResource(fileName)).getFile());
        Scanner fl = new Scanner(file);
        while (fl.hasNextLine())
            System.out.println(fl.nextLine());
        fl.close();
    }

    static boolean CommandRecognition(String str, ZmeyGorinich zmey, Hero hero, HashMap<String, Weapon> map) {
        Scanner sc = new Scanner(System.in);
        Marker:
        while (true) {
            switch (str.toUpperCase()) {
                case "МЕЧ":
                    zmey.setHealth(zmey.getHealth() - hero.hit(map, "МЕЧ"));
                    break Marker;
                case "ЛУК":
                    zmey.setHealth(zmey.getHealth() - hero.hit(map, "ЛУК"));
                    break Marker;
                case "КОПЬЕ":
                    zmey.setHealth(zmey.getHealth() - hero.hit(map, "КОПЬЕ"));
                    break Marker;
                case "РЕГЕН":
                    hero.plusHealth(hero.getHealth(), hero.getValuesRegen());
                    break Marker;
                case "ВЫХОД":
                    return false;
                default:
                    System.out.print("Попробуй еще раз: ");
                    str = sc.next();
                    break;
            }
        }
        return true;
    }
}



