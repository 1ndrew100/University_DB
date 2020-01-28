package com.university.cli;

import com.university.db.UniversityDB;

import java.io.IOException;
import java.util.Scanner;

public class CLI {
    public static void main(String[] args) throws IOException {
        UniversityDB universityDB = new UniversityDB();
        universityDB.setPaths("groups.csv", "students.csv", "disciplines.csv", "marks.csv");

        universityDB.loadAllCSV();

        Scanner scanner = new Scanner(System.in);

        Menu mainMenu = new Menu(scanner);
        Menu editMenu = new Menu(scanner);
        Menu printMenu = new Menu(scanner);

        mainMenu.putMenuItem("1", "Print data", printMenu::loop);
        mainMenu.putMenuItem("2", "Edit data", editMenu::loop);
        mainMenu.putMenuItem("3", "Exit", mainMenu::stop);

        printMenu.putMenuItem("1", "Print groups", universityDB.getGroupsManager()::print);
        printMenu.putMenuItem("2", "Print students", universityDB.getStudentsManager()::print);
        printMenu.putMenuItem("3", "Print disciplines", universityDB.getDisciplinesManager()::print);
        printMenu.putMenuItem("4", "Print marks", universityDB.getMarksManager()::print);
        printMenu.putMenuItem("5", "Print students info", universityDB::printStudentsInfo);
        printMenu.putMenuItem("6", "Return", printMenu::stop);

        editMenu.putMenuItem("1", "Edit group", "Enter: group_id new_name", universityDB.getGroupsManager()::edit);
        editMenu.putMenuItem("2", "Edit student", "Enter: student_id new_first_name new_last_name new_group_id", universityDB.getGroupsManager()::edit);
        editMenu.putMenuItem("3", "Edit discipline", "Enter: discipline_id new_discipline_name", universityDB.getDisciplinesManager()::edit);
        editMenu.putMenuItem("4", "Edit mark", "Enter: mark_id new_mark", universityDB.getMarksManager()::edit);
        editMenu.putMenuItem("5", "Save changes", universityDB::saveAll);
        editMenu.putMenuItem("6", "Return", editMenu::stop);

        mainMenu.loop();
    }
}
