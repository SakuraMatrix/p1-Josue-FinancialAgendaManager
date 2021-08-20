package com.elwinJ.financialagendamanager.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.elwinJ.financialagendamanager.domain.Task;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
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
        ArrayList<String> parsedParams = parseSetDescription(params);

        SimpleStatement stmt = SimpleStatement.builder("DELETE FROM task_database.tasks WHERE setname = ? AND description = ?")
                .addPositionalValues(parsedParams.get(0),parsedParams.get(1)).build();

        Flux.from(session.executeReactive(stmt)).subscribe();

        return "Record removed";
    }

    public String updateTask(String params){
        ArrayList<String> parasedParams = parseSetDescription(params);

        SimpleStatement stmt = SimpleStatement.builder("UPDATE task_database.tasks SET status = 'completed' WHERE setname = ? AND description = ? IF EXISTS")
                .addPositionalValues(parasedParams.get(0),parasedParams.get(1)).build();

        Flux.from(session.executeReactive(stmt)).subscribe();
        return "Record Updated";
    }

    private ArrayList<String> parseSetDescription(String str){
        ArrayList<String> stringArray = new ArrayList<String>();
        String[] parsedParams = str.split("::::");
        stringArray.add(parsedParams[0]); //setName
        stringArray.add(parsedParams[1]); //description

        //System.out.println(setname);
        //System.out.println(description);
        return stringArray;
    }

//    public static void main(String[] args) {
//        removeTask("Set1::::Task=Finish Project 1");
//
//
//    }
}
