package src.exe;

import src.daos.GroupDao;
import src.models.Group;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GroupExe {

    public static void main(String[] args) {

        GroupDao groupDao = new GroupDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        String answer = "";

        while (!answer.equals("return")) {

            pw.println("Menu edycji grup:");
            pw.println("Mozliwe opcje: ");
            pw.println("1. show - pokaz liste grup");
            pw.println("2. add - dodawanie nowej grupy");
            pw.println("3. edit - edycja juz istniejacego grupy");
            pw.println("4. delete - usuniecia grupy");
            pw.println("5. return - powrot do glownego ekranu");
            pw.println("6. quit - zamkniecie programu");
            answer = scan.nextLine();

            switch (answer) {
                case "show":
                    pw.println("ID / Nazwa grupy");
                    pw.println(Arrays.toString(groupDao.findAll()));
                    break;
                case "add":
                    add();
                    break;
                case "edit":
                    edit();
                    break;
                case "delete":
                    delete();
                    break;
                case "quit":
                    System.exit(0);
            }
        }
    }

    public static void add() {
        GroupDao groupDao = new GroupDao();
        Scanner scanString = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println("Dodajemy nowa grupe");
        pw.println("Podaj nazwe grupy");

        String name = scanString.nextLine();
        Group group = new Group(name);

        pw.println("Czy jestes pewny ze chcesz utworzyc grupe o nazwie?" + name);
        pw.println("yes/no");

        while (true) {
            String answer = scanString.nextLine().trim().toLowerCase();

            if (answer.equals("yes")) {
                groupDao.create(group);
                pw.println("Grupa o id: " + group.getId() + " i nazwie " + group.getName());
                break;
            } else if (answer.equals("no")) {
                pw.println("operacja została wstrzymana! + \n");
                break;
            } else {
                pw.println("Blad. Prosze wpisac yes albo no");
            }
        }
    }

    public static void edit() {

        GroupDao groupDao = new GroupDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println("Edyujesz grupy");
        int groupId = getGroup();

        pw.println("Jestes w trakcie edytowania uzytkownika: ");
        pw.println(groupDao.read(groupId));

        pw.println("Podaj nowa nazwe grupy");
        String s = scan.nextLine();

        Group group = new Group(s);
        group.setId(groupId);

        groupDao.update(group);
        pw.println("Zostaly zmienione dane dla grupy o ID " + groupId);
        pw.println("Aktualne dane tego uzytkownika to:");
        pw.println(groupDao.read(groupId));
    }

    public static void delete() {

        GroupDao groupDao = new GroupDao();
        Scanner scanString = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println("Podaj id groupy ktorej chcesz skasowac");
        int groupId = getGroup();

        pw.println("Czy jestes pewny ze chcesz skasowac grupe? ");
        pw.println(groupDao.read(groupId));
        pw.println("yes/no");

        while (true) {
            String answer = scanString.nextLine().trim().toLowerCase();

            if (answer.equals("yes")) {
                groupDao.delete(groupId);
                pw.println("grupa zostalo usuniety o ID " + groupId);
                break;
            } else if (answer.equals("no")) {
                pw.println("operacja została wstrzymana! + \n");
                break;
            } else {
                pw.println("Blad. Prosze wpisac yes albo no");
            }
        }
    }

    public static int getGroup() {
        GroupDao groupDao = new GroupDao();
        Scanner scanInt = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        boolean check = false;
        int groupId = -1;

        while (!check) {
            pw.println("ID / Nazwa grupy");
            pw.println(Arrays.toString(groupDao.findAll()));
            pw.println("Podaj numer grupy:");
            groupId = scanInt.nextInt();
            if (groupId > 0 && groupDao.read(groupId) != null) {
                check = true;
            } else {
                pw.println("Taka grupa nie istnieje");
                pw.println("Wczytac ponownie liste grup?");
                pw.println("yes/no");

                while (true) {
                    String answer = scanString.nextLine().trim().toLowerCase();

                    if (answer.equals("yes")) {
                        pw.println("Lista grup:");
                        pw.println("ID / Nazwa grupy");
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
