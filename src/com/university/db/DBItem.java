package com.university.db;

public abstract class DBItem {
    private int id;

    public DBItem(String[] rawValues) { id = Integer.parseInt(rawValues[0]); }

    public int getId() {
        return id;
    }

    abstract public void parseData(String[] rawValues);

    abstract public String getCSVFormat();

    public abstract String[] getHeaders();

    public abstract String getPrintFormat();
}
