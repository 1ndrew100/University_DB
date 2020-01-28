package com.university.cli;

import java.io.IOException;
import java.util.function.Consumer;

public class MenuItem {
    String name;
    String question;
    Consumer<String[]> consumer;
    Function function;

    public MenuItem(String name, Function action) {
        this.name = name;
        this.function = action;
    }

    public MenuItem(String name, String query, Consumer<String[]> action) {
        this.name = name;
        this.question = query;
        this.consumer = action;
    }

    public void printText() {
        if (question != null)
            System.out.println(question);
    }

    public String getName() {
        return name;
    }

    public void execute() throws IOException {
        function.execute();
    }

    public void execute(String[] rawValues) {
        consumer.accept(rawValues);
    }

    public boolean requireArguments() {
        return consumer != null;
    }
}