package io.escoffier.demo;

import io.vertx.reactivex.core.http.HttpServerRequest;

import java.util.UUID;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class MyState {

    private final String id = UUID.randomUUID().toString();

    private long begin;

    private String message;
    private HttpServerRequest request;

    public long getBegin() {
        return begin;
    }

    public MyState setBegin(long begin) {
        this.begin = begin;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MyState setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

    public MyState setRequest(HttpServerRequest req) {
        this.request = req;
        return this;
    }

    public HttpServerRequest getRequest() {
        return request;
    }
}
