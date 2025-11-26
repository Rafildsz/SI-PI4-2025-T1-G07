package server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.*;

public class StaticFileHandler implements HttpHandler {

    private final Path baseDir;

    public StaticFileHandler(String baseDir) {
        this.baseDir = Paths.get(baseDir);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String filePath = exchange.getRequestURI().getPath();

        if (filePath.equals("/")) {
            filePath = "/index.html";
        }

        Path resolvedPath = baseDir.resolve(filePath.substring(1)).normalize();

        if (!resolvedPath.startsWith(baseDir) || !Files.exists(resolvedPath)) {
            String msg = "Arquivo não encontrado: " + filePath;
            exchange.sendResponseHeaders(404, msg.length());
            exchange.getResponseBody().write(msg.getBytes());
            exchange.close();
            return;
        }

        byte[] fileBytes = Files.readAllBytes(resolvedPath);

        exchange.sendResponseHeaders(200, fileBytes.length);
        exchange.getResponseBody().write(fileBytes);
        exchange.close();
    }
}
