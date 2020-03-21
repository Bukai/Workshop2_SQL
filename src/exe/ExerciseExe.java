package src.exe;

import src.daos.ExerciseDao;
import src.models.Exercise;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExerciseExe {

    public static void main(String[] args) {
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        String answer = "";

        while (!answer.equals("return")) {

            pw.println("Menu edycji zadan:");
            pw.println("Mozliwe opcje: ");
            pw.println("1. show - pokaz istniejace zdania");
            pw.println("2. add - dodawanie nowego zadania");
            pw.println("3. edit - edycja juz istniejacego zadania");
            pw.println("4. delete - usuniecia zadania");
            pw.println("5. return - powrot do glownego ekranu");
            pw.println("6. quit - zamkniecie programu");
            answer = scan.nextLine();

            switch (answer) {
                case "show":
                    pw.println("ID / Tytul / Tresc");
                    pw.println(Arrays.toString(exerciseDao.findAll()));
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
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println("Dodajesz nowego zadanie");
        pw.println("Prosze o podanie nastepujacych danych");

        pw.println("Tytulu zadania");
        String title = scan.nextLine();

        pw.println("Tresci zadania:");
        String description = scan.nextLine();

        Exercise exercise = new Exercise(title, description);
        exerciseDao.create(exercise);

        pw.println("Gratuluje utworzyles nowe zadanie");
        pw.print("Tytul nowego zadania to " + exercise.getTitle() + "\n");
    }

    public static void edit() {
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        int exerciseId = getExerciseId();

        pw.println("Zmieniasz nastepujace zadanie: ");
        pw.println(exerciseDao.read(exerciseId));

        pw.println("Tytulu zadania");
        String title = scan.nextLine();

        pw.println("Tresci zadania:");
        String description = scan.nextLine();

        Exercise exercise = new Exercise(title, description);
        exercise.setId(exerciseId);
        exerciseDao.update(exercise);
    }

    public static void delete() {
        ExerciseDao exerciseDao = new ExerciseDao();
        PrintWriter pw = new PrintWriter(System.out, true);
        Scanner scanString = new Scanner(System.in);

        int exerciseId = getExerciseId();
        pw.println("Czy jestes pewny ze chcesz skasowac ponizsze zadanie ?");
        pw.println(exerciseDao.read(exerciseId));
        pw.println("yes/no");

        while (true) {
            String answer = scanString.nextLine().trim().toLowerCase();

            if (answer.equals("yes")) {
                exerciseDao.delete(exerciseId);
                pw.println("Zadanie zostalo usuniety o ID " + exerciseId);
                break;
            } else if (answer.equals("no")) {
                pw.println("operacja została wstrzymana! + \n");
                break;
            } else {
                pw.println("Blad. Prosze wpisac yes albo no");
            }
        }
    }

    public static int getExerciseId() {
        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scan = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out, true);

        int exerciseId = -1;
        boolean check = false;

        while (!check) {
            try {
                pw.println("Podaj ID zadania ktore chcesz zmienic");
                exerciseId = scan.nextInt();

                if (exerciseDao.read(exerciseId) != null) {
                    check = true;
                } else {
                    pw.println("Bledne ID zadania.");
                    scan.nextLine();
                }
            } catch (InputMismatchException e) {
                pw.println("Bledne ID zadania.");
                scan.nextLine();
            }
        }
        return exerciseId;
    }
}
