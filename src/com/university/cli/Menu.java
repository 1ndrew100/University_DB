package com.university.cli;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Menu {
    private boolean enabled;
    private Scanner scanner;
    private HashMap<String, MenuItem> actions;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
        actions = new HashMap<>();
    }

    public void loop() throws IOException {
        enabled = true;
        while (enabled) {
            this.printMenu();
            this.executeAction(scanner.nextLine());
            System.out.println();
        }
    }

    public void putMenuItem(String key, String name, Function action) {
        actions.put(key, new MenuItem(name, action));
    }

    public void putMenuItem(String key, String name, String query, Consumer<String[]> action) {
        actions.put(key, new MenuItem(name, query, action));
    }

    public void stop() {
        enabled = false;
    }

    private void printMenu() {
        for (Map.Entry<String, MenuItem> actionEntry : actions.entrySet())
            System.out.println("(" + actionEntry.getKey() + ") " + actionEntry.getValue().getName());
    }

    private void executeAction(String key) throws IOException {
        actions.get(key).printText();
        if (actions.get(key).requireArguments())
            actions.get(key).execute(scanner.nextLine().split(" "));
        else
            actions.get(key).execute();
    }
}
