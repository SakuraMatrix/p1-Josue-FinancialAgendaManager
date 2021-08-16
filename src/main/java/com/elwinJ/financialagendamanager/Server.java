package com.elwinJ.financialagendamanager;

import com.datastax.oss.driver.api.core.CqlSession;
//import com.elwinJ.pokemart.domain.Item;
//import com.elwinJ.pokemart.repository.ItemRepository;
//import com.elwinJ.pokemart.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBufAllocator;
import reactor.netty.http.server.HttpServer;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {

    public void startServer() throws URISyntaxException {
        Path LandingHTML = Paths.get(App.class.getResource("/LandingPage.html").toURI());
        HttpServer.create()
                .port(64300)
                .route(routes ->
                        routes.get("/", (request,response) ->
                                response.sendFile(LandingHTML))).bindNow().onDispose().block();
    }
}
