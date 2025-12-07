//Autor: Isabelly Horschutz
package server;

import com.sun.net.httpserver.HttpServer;
import server.handler.ProxyHandler;
import server.handler.StaticFileHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainServer {

    public static void main(String[] args) {
        try {
            int port = 8080;

            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            // Teste
            server.createContext("/ping", exchange -> {
                String response = "Servidor Java puro está rodando!";
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.close();
            });

            // Rota que repassa para o backend Spring Boot
            String backendBase = "http://localhost:8081";
            server.createContext("/api", new ProxyHandler(backendBase));

            // Servir arquivos do front
            server.createContext("/", new StaticFileHandler(
                    "C:/Users/maysa/Documents/SI-PI4-2025-T1-G07"
            ));

            server.setExecutor(null);
            server.start();

            System.out.println(" Proxy rodando em http://localhost:" + port);
            System.out.println("Servindo FRONT de: SI-PI4-2025-T1-G07");
            System.out.println("Repassando /api para backend: http://localhost:8081");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
