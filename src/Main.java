package src;

import src.exe.ExerciseExe;
import src.exe.GroupExe;
import src.exe.SolutionExe;
import src.exe.UserExe;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String answer = "";

        while (!answer.equals("quit")) {

            Scanner scan = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(System.out, true);

            pw.println("Witamy z Panelu Administracyjnym szkoly programowania");
            pw.println("Co chcesz zrobic?");
            pw.println("1. Operacje na uzytkownikach (dodawanie, usuwanie, edytowanie) - wypisz: users");
            pw.println("2. Operacje na grupach - wpisz: groups");
            pw.println("3. Operacje na zadaniach - wpisz: exercises");
            pw.println("4. Operacje na rozwiazaniach - wpisz: solutions");
            pw.println("5. Wpisz quit w celu zamkniecia programu");
            answer = scan.nextLine();
            String[] s = new String[1];

            switch (answer) {
                case "users":
                    UserExe.main(s);
                    break;
                case "exercises":
                    ExerciseExe.main(s);
                    break;
                case "groups":
                    GroupExe.main(s);
                    break;
                case "solutions":
                    SolutionExe.main(s);
                    break;
            }

        }

    }
}
