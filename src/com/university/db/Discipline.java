package com.university.db;

public class Discipline extends DBItem {
    private String name;
    private String[] headers;
    private String printFormat;

    public Discipline(String[] rawValues) {
        super(rawValues);
        this.parseData(rawValues);
        headers = new String[] {"id", "discipline"};
        printFormat = "%3s%16s";
    }

    public String getName() { return name; }

    @Override
    public String[] getHeaders() {
        return headers;
    }

    @Override
    public String getPrintFormat() {
        return printFormat;
    }

    @Override
    public void parseData(String[] rawValues) {
        this.name = rawValues[1];
    }

    @Override
    public String getCSVFormat() {
        return getId() + "," + getName();
    }

    @Override
    public String toString() {
        return String.format(getPrintFormat(), getId(), getName());
    }
}
