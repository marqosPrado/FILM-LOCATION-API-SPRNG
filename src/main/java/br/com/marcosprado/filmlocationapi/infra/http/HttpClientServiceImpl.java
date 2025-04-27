package br.com.marcosprado.filmlocationapi.infra.http;

import br.com.marcosprado.filmlocationapi.port.http.HttpService;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class HttpClientServiceImpl implements HttpService {
    private final HttpClient httpClient;
    private final int SECONDS_TIMEOUT = 10;

    public HttpClientServiceImpl() {
        httpClient = HttpClient.newHttpClient();
    }

    @Override
    public String get(URI uri) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.ofSeconds(SECONDS_TIMEOUT))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return httpClient
                .send(request, HttpResponse.BodyHandlers.ofString())
                .body();
    }
}
