package com.elwinJ.financialagendamanager;

import java.net.URISyntaxException;

public class App {

    public static void main(String[] args) throws URISyntaxException {
        Server myServer = new Server();
        myServer.startServer();

    }
}
