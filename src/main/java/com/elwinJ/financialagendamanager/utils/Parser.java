package com.elwinJ.financialagendamanager.utils;

import com.elwinJ.financialagendamanager.domain.Task;

import java.lang.reflect.Array;

import static com.elwinJ.financialagendamanager.utils.Mapper.OBJECT_MAPPER;


public class Parser {

    public static Task parseTask(String str){
        Task task = null;
        try{
            task = OBJECT_MAPPER.readValue(str, Task.class);
        } catch (Exception e) {
            e.printStackTrace();
            //put more parsing handling here in case program can't handle return data from html forms.
            String[] request = str.split("&");
            String setname = request[0].split("=")[1];
            String description = request[1].split("=")[1];
            description = String.join(" ", description.split("\\+")); //Split off the + symbol, followed by joining empty space.
            String duedate = request[2].split("=")[1];
            task = new Task(setname,description,duedate,"on-going");
        }

        return task;
    }

    public static void main(String[] args){
        String test = "{\"setName\":\"Set2\",\"description\":\"Check dailes in RTS\",\"dueDate\":\"2021-09-01\",\"status\":\"on-going\"}";
        Task task = parseTask(test);
        System.out.println(task.getSetName());
        System.out.println(task.getDescription());
        System.out.println(task.getDueDate());
        System.out.println(task.getStatus());

    }
}
