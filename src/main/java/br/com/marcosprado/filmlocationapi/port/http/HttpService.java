package br.com.marcosprado.filmlocationapi.port.http;

import java.net.URI;

public interface HttpService {
    String get(URI url) throws Exception;
}
