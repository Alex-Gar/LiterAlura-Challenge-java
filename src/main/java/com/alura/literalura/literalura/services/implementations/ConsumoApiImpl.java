package com.alura.literalura.literalura.services.implementations;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApiImpl implements ConsumoApi {

    @Override
    public String ConsumoApi(String url) {
        HttpResponse<String> httpResponse = null;

        // Construyendo el Cliente para Solicitudes (HttpClient)
        HttpClient httpClient = HttpClient.newHttpClient();
        // Construyendo la Solicitud (HttpRequest)
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        // Construyendo la Respuesta (HttpResponse)
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String jsosn = httpResponse.body();
        return jsosn;
    }

}
