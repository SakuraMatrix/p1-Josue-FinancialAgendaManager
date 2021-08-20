package com.elwinJ.financialagendamanager.service;

import com.elwinJ.financialagendamanager.domain.Task;
import com.elwinJ.financialagendamanager.repository.TaskRepository;
import reactor.core.publisher.Flux;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Flux<Task> getAllFromSet(String setName){
        return taskRepository.getAllFromSet(setName);
    }

    public Flux<Task> getAll() {
        return taskRepository.getAll();
    }

    public Task createTask(Task task){
        return taskRepository.createTask(task);
    }

    public String removeTask(String params) {return taskRepository.removeTask(params);}

    public String updateTask(String params) {return taskRepository.updateTask(params);}
}
