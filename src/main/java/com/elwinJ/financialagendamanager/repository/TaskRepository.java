package com.elwinJ.financialagendamanager.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.elwinJ.financialagendamanager.domain.Task;
import reactor.core.publisher.Flux;

import java.util.Date;

public class TaskRepository {
    private CqlSession session;

    public TaskRepository(CqlSession session) {
        this.session = session;
    }

    public Flux<Task> getAllFromSet(String setName){
        return Flux.from(session.executeReactive("SELECT * FROM task_database.tasks WHERE setname = '" + setName+"'"))
                .map(row ->
                        new Task(row.getString("setname")
                                ,row.getString("description")
                                ,row.getString("duedate")
                                ,row.getString("status")));
    }

    public Flux<Task> getAll(){
        return Flux.from(session.executeReactive("SELECT * FROM task_database.tasks"))
                .map(row ->
                        new Task(row.getString("SetName")
                                ,row.getString("Description")
                                ,row.getString("DueDate")
                                ,row.getString("Status")));
    }

    public Task createTask(Task task) {
        SimpleStatement stmt = SimpleStatement.builder("INSERT INTO task_database.tasks (setname, description, duedate, status) VALUES (?, ?, ?, ?)")
                .addPositionalValues(task.getSetName(), task.getDescription(), task.getDueDate(), task.getStatus())
                .build();

        Flux.from(session.executeReactive(stmt)).subscribe();

        return task;
    }

    public String removeTask(String params) {
        String[] parsedParams = params.split("::::");
        String setname = parsedParams[0];
        String description = parsedParams[1];
        System.out.println(setname);
        System.out.println(description);

        SimpleStatement stmt = SimpleStatement.builder("DELETE FROM task_database.tasks WHERE setname = ? AND description = ?")
                .addPositionalValues(setname,description).build();

        Flux.from(session.executeReactive(stmt)).subscribe();

        return "Record removed";
    }

//    public static void main(String[] args) {
//        removeTask("Set1::::Task=Finish Project 1");
//
//
//    }
}
