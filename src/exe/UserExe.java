package src.exe;

import src.daos.GroupDao;
import src.daos.UserDao;
import src.models.User;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class UserExe {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        String answer = "";

        while (!answer.equals("return")) {

            pw.println("Jestes w menu edycji uzytkownika");
            pw.println("Mozliwe opcje: ");
            pw.println("1. show - pokaz lista uzytkownikow");
            pw.println("2. add - dodaj nowego uzytkownika");
            pw.println("3. edit - edycja juz istniejacego uzytkownika");
            pw.println("4. delete - usuniecia uzytkownika");
            pw.println("5. return - powrot do glownego ekranu");
            pw.println("6. quit - zamkniecie programu");
            answer = scan.nextLine();

            switch (answer) {
                case "show":
                    pw.println("ID / Name / Email / GroupID");
                    pw.println(Arrays.toString(userDao.findAll()));
                    break;
                case "add":
                    addUser();
                    break;
                case "edit":
                    editUser();
                    break;
                case "delete":
                    deleteUser();
                    break;
                case "quit":
                    System.exit(0);
                default:
                    pw.println("Bład! Sprobuj jeszcze raz! \n");
                    break;
            }
        }

    }

    public static void addUser() {
        UserDao userDao = new UserDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println("Dodawanie nowego uzytkownika");

        pw.println("Podaj nazwe uzytkownika:");
        String name = scan.nextLine();

        String email = getMail();

        pw.println("Podaj haslo uzytkownika");
        String password = scan.nextLine();

        int groupId = getGroup();

        User user = new User(name, email, password, groupId);
        userDao.create(user);

        pw.println("Uzytkownik dodany");
    }

    public static void editUser() {
        UserDao userDao = new UserDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println("Podaj Id uzytkownika do edycji");
        int userId = getUser();

        pw.println("Jestes w trakcie edytowania uzytkownika: ");
        pw.println(userDao.read(userId));

        pw.println("Podaj nowa nazwe uzytkownika: ");
        String name = scan.next();

        String email = getMail();

        pw.println("Podaj nowe haslo uzytkownika: ");
        String password = scan.next();

        int groupId = getGroup();

        User user = new User(name, email, password, groupId);
        user.setId(userId);
        userDao.update(user);

        pw.println("Zostaly zmienione dane dla uzytkownika o ID " + userId);
    }

    public static void deleteUser() {
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println("Jestes w menu usuwania uzytkownika!");
        int userId = getUser();

        UserDao userDao = new UserDao();
        pw.println(userDao.read(userId));
        pw.println("Czy jestes pewny ze chcesz skasowac tego uzytkownika?");
        pw.println("yes/no");

        while (true) {
            String answer = scan.nextLine().trim().toLowerCase();

            if (answer.equals("yes")) {
                userDao.delete(userId);
                pw.println("User zostal usuniety o ID " + userId);
                break;
            } else if (answer.equals("no")) {
                pw.println("operacja została wstrzymana! + \n");
                break;
            } else {
                pw.println("Blad. Prosze wpisac yes albo no");
            }
        }

    }

    public static int getUser() {
        UserDao userDao = new UserDao();
        Scanner scanInt = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        int userId = -1;
        boolean check = false;

        pw.println("Lista uzytkownikow:");
        pw.println("ID / Name / Email / GroupID");
        pw.println(Arrays.toString(userDao.findAll()));

        while (!check) {
            pw.println("Podaj id uzytkownika ktorego chcesz edytowac");
            userId = scanInt.nextInt();

            if (userId > 0 && userDao.read(userId) != null) {
                check = true;
            } else {
                pw.println("Uzytkownik o takim Id nie istnieje");
                pw.println("Wczytac ponownie liste uzytkownikow?");
                pw.println("yes/no");

                while (true) {
                    String answer = scanString.nextLine().trim().toLowerCase();

                    if (answer.equals("yes")) {
                        pw.println("ID / Name / Email / GroupID");
                        pw.println(Arrays.toString(userDao.findAll()));
                        break;
                    } else if (answer.equals("no")) {
                        break;
                    } else {
                        pw.println("Blad. Prosze wpisac yes albo no");
                    }
                }
            }
        }
        return userId;
    }

    public static String getMail() {
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        String email = null;
        boolean check = false;

        while (!check) {
            pw.println("Podaj adres mailowy: ");
            email = scan.nextLine();
            if (email.contains("@") && email.length() > 4) {
                check = true;
            } else {
                pw.println("Błedny adres email! Adres posiada przynajmniej 5 znakow i jednym z nich jest @");
                pw.println("Podaj poprawny adres email!");
                scan.nextLine();
            }
        }
        return email;
    }

    public static int getGroup() {
        GroupDao groupDao = new GroupDao();
        Scanner scanInt = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        boolean check = false;
        int groupId = -1;

        pw.println("Istniejace grupy: ");
        pw.println(Arrays.toString(groupDao.findAll()));

        while (!check) {
            pw.println("Podaj numer grupy do ktorej nalezy uzytkownik");

            groupId = scanInt.nextInt();
            if (groupId > 0 && groupDao.read(groupId) != null) {
                check = true;
            } else {
                pw.println("Taka grupa nie istnieje");
                pw.println("Wczytać ponownie liste grup?");
                pw.println("yes/no");

                while (true) {
                    String answer = scanString.nextLine().trim().toLowerCase();

                    if (answer.equals("yes")) {
                        pw.println("Istniejace grupy: ");
                        pw.println(Arrays.toString(groupDao.findAll()));
                        break;
                    } else if (answer.equals("no")) {
                        break;
                    } else {
                        pw.println("Blad. Prosze wpisac yes albo no");
                    }
                }
            }
        }
        return groupId;
    }

}
