package com.elwinJ.financialagendamanager.utils;

import com.datastax.oss.driver.api.core.CqlSession;
import com.elwinJ.financialagendamanager.repository.TaskRepository;

public class CassandraConnection {

    public static CqlSession establishConnection(){
        return CqlSession.builder().build();
    }

    public static  TaskRepository injectToTaskRepository(){
        return new TaskRepository(establishConnection());
    }
}
