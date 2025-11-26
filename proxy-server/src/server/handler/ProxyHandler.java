package server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProxyHandler implements HttpHandler {

    private final String backendBase;

    public ProxyHandler(String backendBase) {
        this.backendBase = backendBase;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String targetUrl = backendBase + exchange.getRequestURI().toString();

        URL url = new URL(targetUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(exchange.getRequestMethod());
        conn.setDoOutput(true);

        // Copia headers
        exchange.getRequestHeaders().forEach((k, v) -> conn.setRequestProperty(k, String.join(",", v)));

        // Copia o corpo da requisição (POST/PUT)
        if (exchange.getRequestBody() != null) {
            try (OutputStream os = conn.getOutputStream()) {
                exchange.getRequestBody().transferTo(os);
            }
        }

        // Corpo da resposta do backend
        InputStream response = conn.getInputStream();
        byte[] responseBytes = response.readAllBytes();

        exchange.sendResponseHeaders(conn.getResponseCode(), responseBytes.length);
        exchange.getResponseBody().write(responseBytes);
        exchange.close();
    }
}
