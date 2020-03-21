package src.exe;

import src.daos.ExerciseDao;
import src.daos.SolutionDao;
import src.daos.UserDao;
import src.models.Solution;
import src.models.User;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class SolutionExe {

    public static void main(String[] args) {
        SolutionDao solutionDao = new SolutionDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        String answer = "";

        while (!answer.equals("return")) {



            pw.println("Menu edycji rozwiazan do zadan:");
            pw.println("Mozliwe opcje: ");
            pw.println("1. show - pokaz lista solutions");
            pw.println("2. add - dodawanie nowego rozwiazania");
            pw.println("3. view - przegladanie rozwiazan uzytkownika");
            pw.println("4. delete - usuniecie solution");
            pw.println("5. return - powrot do glownego ekranu");
            pw.println("6. quit - zamkniecie programu");
            answer = scan.nextLine();

            switch (answer) {
                case "show":
                    pw.println(Arrays.toString(solutionDao.findAll()));
                    break;
                case "add":
                    addSolution();
                    break;
                case "view":
                    viewSolution();
                    break;
                case "delete":
                    deleteSolution();
                    break;
                case "quit":
                    System.exit(0);
            }
        }
    }

    public static void addSolution() {

        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();
        SolutionDao solutionDao = new SolutionDao();
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        int id;
        boolean check = false;

        pw.println("ID / Name / Email / GroupID");
        pw.println(Arrays.toString(userDao.findAll()));

        while (!check){
            pw.println("Podaj id uzytkownika: ");
            while (!scannerInt.hasNextInt()){
                scannerInt.next();
                pw.println("To nie jest numer! Podaj id uzytkownika:");
            }
            id = scannerInt.nextInt();


            if (id > 0 && userDao.read(id) != null) {
                pw.println(Arrays.toString(exerciseDao.findAll()));

                pw.println("Podaj id zadania: ");
                int exerciseId = scannerInt.nextInt();

                Date dt = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(dt);

                Solution solution = new Solution(currentTime, "", exerciseId, id);
                solutionDao.create(solution);

                pw.println("Utworzono zadanie");

                check = true;
            } else {
                pw.println("Uzytkownik o takim Id nie istnieje");
                pw.println("Wczytac ponownie liste uzytkownikow?");
                pw.println("1. show - lista uzytkownikow");
                pw.println("2. return - powrot");
                while (true){
                    String answer = scannerString.nextLine().trim().toLowerCase();

                    if (answer.equals("show")) {
                        pw.println("Lista uzytkownikow:");
                        pw.println(Arrays.toString(userDao.findAll()));
                        break;
                    } else if (answer.equals("return")){
                        break;
                    } else {
                        pw.println("Blad. Prosze wpisac show lub return");
                    }
                }
            }
        }
    }

    public static void viewSolution() {
        UserDao userDao = new UserDao();
        SolutionDao solutionDao = new SolutionDao();
        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println("ID / Name / Email / GroupID");
        pw.println(Arrays.toString(userDao.findAll()));

        pw.println("Podaj id uzytkownika: ");
        int id = scanner.nextInt();

        pw.println(solutionDao.read(id));
    }

    public static void deleteSolution() {
        SolutionDao solutionDao = new SolutionDao();
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println(Arrays.toString(solutionDao.findAll()));

        int solutionId = getSolutionId();
        pw.println("Czy jestes pewny ze chcesz skasowac ponizsze zadanie ?");
        pw.println(solutionDao.read(solutionId));
        pw.println("1. yes - usun solution");
        pw.println("2. no - przerwij operacje");

        Scanner scan = new Scanner(System.in);

        while (true){
            String answer = scan.nextLine().trim().toLowerCase();
            if (answer.equals("yes")) {
                solutionDao.delete(solutionId);
                pw.println("Solution zostalo usuniety o ID " + solutionId);
                break;
            } else if (answer.equals("no")){
                pw.println("operacja została wstrzymana! + \n");
                break;
            } else {
                pw.println("Blad. Prosze wpisac yes albo no");
            }
        }
    }

    public static int getSolutionId() {
        SolutionDao solutionDao = new SolutionDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        int solutionId = -1;
        boolean check = false;

        while (!check) {

            pw.println("Podaj ID solution ktore chcesz zmienic");
            solutionId = scan.nextInt();

            if (solutionDao.read(solutionId) != null) {
                check = true;
            } else {
                pw.println("Bledne ID solution.");
                pw.println("Prosze podac ID solution");

                while (!scan.hasNextInt()){
                    scan.next();
                    pw.println("To nie jest numer! Podaj id solution:");
                }
                scan.nextInt();
            }
        }
        return solutionId;
    }
}
