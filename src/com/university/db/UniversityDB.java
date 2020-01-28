package com.university.db;

public class UniversityDB extends DataBase {
    ItemsManager<Group> groupsManager;
    ItemsManager<Student> studentsManager;
    ItemsManager<Discipline> disciplinesManager;
    ItemsManager<Mark> marksManager;

    public UniversityDB() {
        groupsManager = this.newItemsManager(Group::new);
        studentsManager = this.newItemsManager(Student::new);
        disciplinesManager = this.newItemsManager(Discipline::new);
        marksManager = this.newItemsManager(Mark::new);
    }

    public void setPaths(String groupsPath, String studentsPath, String disciplinesPath, String marksPath) {
        groupsManager.setCsvPath(groupsPath);
        studentsManager.setCsvPath(studentsPath);
        disciplinesManager.setCsvPath(disciplinesPath);
        marksManager.setCsvPath(marksPath);
    }

    public ItemsManager<Group> getGroupsManager() {
        return groupsManager;
    }

    public ItemsManager<Student> getStudentsManager() {
        return studentsManager;
    }

    public ItemsManager<Discipline> getDisciplinesManager() {
        return disciplinesManager;
    }

    public ItemsManager<Mark> getMarksManager() {
        return marksManager;
    }

    public void printStudentsInfo() {
        // format: id first_name last_name group_name avg_mark
        String format = "%3s%16s%16s%16s%16s";
        for (Integer sId : studentsManager.getIdSet()) {
            System.out.printf(format, sId, studentsManager.getById(sId).getFirstName(), studentsManager.getById(sId).getLastName(),
                    groupsManager.getById(studentsManager.getById(sId).getGroupId()).getName(), this.getStudentAvgMarkById(sId));
        }
    }

    private double getStudentAvgMarkById(Integer sId) {
        double sum = 0;
        int numberOfMarks = 0;

        for (Integer mId : marksManager.getIdSet()) {
            if (marksManager.getById(mId).getStudentId() == sId) {
                sum += marksManager.getById(mId).getMark();
                numberOfMarks++;
            }
        }

        if (numberOfMarks == 0)
            return 0;
        else
            return sum / numberOfMarks;
    }
}
