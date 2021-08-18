package com.elwinJ.financialagendamanager;

import com.datastax.oss.driver.api.core.CqlSession;
//import com.elwinJ.pokemart.domain.Item;
//import com.elwinJ.pokemart.repository.ItemRepository;
//import com.elwinJ.pokemart.service.ItemService;
import com.elwinJ.financialagendamanager.repository.TaskRepository;
import com.elwinJ.financialagendamanager.service.TaskService;
import com.elwinJ.financialagendamanager.utils.CassandraConnection;
import com.elwinJ.financialagendamanager.utils.Mapper;
import com.elwinJ.financialagendamanager.utils.Parser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBufAllocator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.elwinJ.financialagendamanager.utils.CassandraConnection.injectToTaskRepository;
import static com.elwinJ.financialagendamanager.utils.Mapper.toByteBuf;
import com.elwinJ.financialagendamanager.utils.Parser;

public class Server {

    public void startServer() throws URISyntaxException {

        TaskService taskService = new TaskService(injectToTaskRepository());



        Path LandingHTML = Paths.get(App.class.getResource("/LandingPage.html").toURI());
        HttpServer.create()
                .port(64300)
                .route(routes ->
                        routes.get("/", (request,response) ->
                                response.sendFile(LandingHTML))

                                .get("/task-set", (request,response) ->
                                        response.send(taskService.getAll().map(Mapper::toByteBuf)))

                                .get("/task-set/{params}", (request,response) ->
                                        response.send(taskService.getAllFromSet(request.param("params")).map(Mapper::toByteBuf)))
//                                        response.sendString(Mono.just(taskService.getAllFromSet(request.param("params")).toString())))

                                .post("/task-set", (request,response) ->
                                        response.send(request.receive().asString()
                                                .map(Parser::parseTask)
                                                .map(taskService::createTask) //this will initiate the insertion of the data into th database.
                                                .map(Mapper::toByteBuf)
                                                .log("http-server")))

                                .post("/task-set/remove", (request,response) ->
                                response.sendString(request.receive().asString().map(Parser::parseHTML).map(taskService::removeTask)))

//                                .get()

                )
                .bindNow().onDispose().block();


    }

}
