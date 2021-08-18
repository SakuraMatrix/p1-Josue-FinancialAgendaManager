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
}
