package com.university.db;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;


public class ItemsManager<Item extends DBItem> {
    private String csvPath;
    private ItemConstructor<Item> constructor;
    private HashMap<Integer, Item> items;

    public ItemsManager(ItemConstructor<Item> constructor) {
        this.constructor = constructor;
        items = new HashMap<>();
    }

    public String getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }

    public void add(String[] rawValues) {
        items.put(Integer.parseInt(rawValues[0]), constructor.construct(rawValues));
    }

    public void loadCSV() throws IOException {
        Files.lines(Paths.get(csvPath)).skip(1).forEach((line) -> this.add(line.split(",")));
    }

    public Item getById(Integer id) {
        return items.get(id);
    }

    public boolean containsId(Integer id) {
        return items.containsKey(id);
    }

    public void edit(String[] rawValues) {
        Integer id = Integer.parseInt(rawValues[0]);
        if (this.containsId(id))
            this.getById(id).parseData(rawValues);
    }

    public void print() {
        Integer firstId = 0;
        for (Integer id : getIdSet()) {
            firstId = id;
            break;
        }

        System.out.format(getById(firstId).getPrintFormat(), (Object[]) getById(firstId).getHeaders());
        System.out.println();
        for (Integer id : this.getIdSet()) {
            System.out.println(getById(id).toString());
        }
    }

    public void saveCSV() throws IOException {
        Integer firstId = 0;
        for (Integer id : getIdSet()) {
            firstId = id;
            break;
        }

        PrintWriter printWriter = new PrintWriter(getCsvPath());
        printWriter.write(String.join(",", getById(firstId).getHeaders()));
        // write entries
        for (Integer id : this.getIdSet()) {
            String getCsvFormat = getById(id).getCSVFormat();
            printWriter.write("\n" + getById(id).getCSVFormat());
        }
        printWriter.close();
    }

    public Set<Integer> getIdSet() {
        return items.keySet();
    }
}
