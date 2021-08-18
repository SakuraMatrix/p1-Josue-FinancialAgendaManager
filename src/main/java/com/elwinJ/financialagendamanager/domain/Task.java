package com.elwinJ.financialagendamanager.domain;

import java.time.LocalDate;

public class Task {

    private String setName;
    private String description;
    private String dueDate; //The date will be retrieved from an HTML form and proper formatting will be handled in the back-end.
    private String status;

    public Task(String taskSet, String description, String dueDate, String status) {
        this.setName = taskSet;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Task() {
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String taskSet) {
        this.setName = taskSet;
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
