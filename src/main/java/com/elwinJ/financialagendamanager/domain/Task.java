package com.elwinJ.financialagendamanager.domain;

public class Task {

    private String taskSet;
    private String description;
    private String dueDate; //The date will be retrieved from an HTML form and proper formatting will be handled in the back-end.
    private String status;

    public Task(String taskSet, String description, String dueDate, String status) {
        this.taskSet = taskSet;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(String taskSet) {
        this.taskSet = taskSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
