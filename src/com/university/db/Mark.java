package com.university.db;

public class Mark extends DBItem {
    private int studentId;
    private int disciplineId;
    private int mark;
    private String[] headers;
    private String printFormat;

    public Mark(String[] rawValues) {
        super(rawValues);
        this.parseData(rawValues);
        headers = new String[] {"id", "student_id", "discipline_id", "mark"};
        printFormat = "%3s%16s%16s%16s";
    }

    public int getStudentId() {
        return studentId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public int getMark() {
        return mark;
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
        studentId = Integer.parseInt(rawValues[1]);
        disciplineId = Integer.parseInt(rawValues[2]);
        mark = Integer.parseInt(rawValues[3]);
    }

    @Override
    public String toString() {
        return String.format(getPrintFormat(), getId(), getStudentId(), getDisciplineId(), getMark());
    }

    @Override
    public String getCSVFormat() {
        return getId() + "," + getStudentId() + "," + getDisciplineId() + "," + getMark();
    }
}
