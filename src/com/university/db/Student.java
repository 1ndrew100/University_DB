package com.university.db;

public class Student extends DBItem {
    private String firstName;
    private String lastName;
    private int groupId;
    private String[] headers;
    private String printFormat;

    public Student(String[] rawValues) {
        super(rawValues);
        this.parseData(rawValues);
        headers = new String[] {"id", "first_name", "last_name", "group_id"};
        printFormat = "%3s%16s%16s%16s";
    }

    @Override
    public void parseData(String[] rawValues) {
        firstName = rawValues[1];
        lastName = rawValues[2];
        groupId = Integer.parseInt(rawValues[3]);
    }

    @Override
    public String[] getHeaders() {
        return headers;
    }

    @Override
    public String getPrintFormat() {
        return printFormat;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getGroupId() { return groupId; }

    @Override
    public String toString() {
        return String.format(getPrintFormat(), getId(), getFirstName(), getLastName(), getGroupId());
    }

    @Override
    public String getCSVFormat() {
        return getId() + "," + getFirstName() + "," + getLastName() + "," + getGroupId();
    }
}
