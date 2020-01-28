package com.university.db;

public class Group extends DBItem {
    private String name;
    private String[] headers;
    private String printFormat;

    public Group(String[] rawValues) {
        super(rawValues);
        this.parseData(rawValues);
        headers = new String[] {"id", "group_name"};
        printFormat = "%3s%16s";
    }

    public String getName() {
        return name;
    }

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
    public String toString() {
        String tmp = String.format(getPrintFormat(), getId(), getName());
        return String.format(getPrintFormat(), getId(), getName());
    }

    @Override
    public String getCSVFormat() {
        return getId() + "," + getName();
    }
}
