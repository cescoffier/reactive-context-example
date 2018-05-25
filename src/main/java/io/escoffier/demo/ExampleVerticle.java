package io.escoffier.demo;

import io.reactivex.Single;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.core.http.HttpServerRequest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExampleVerticle extends AbstractVerticle {

    @Override
    public void start() {

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(req -> {
            MyContext.init();
            Single.just(req)
                .doOnSuccess(x -> {
                    MyContext.get().setRequest(req).setMessage(req.getParam("name"));
                    System.out.println("Param is " + MyContext.get().getMessage());
                })
                .compose(flowable -> {
                    long delay = (long) (1000 * Math.random());
                    return flowable.delay(delay, TimeUnit.MILLISECONDS);
                })
                .flatMap(x -> proceed())
                .doOnSuccess(res -> {
                    HttpServerRequest request = MyContext.get().getRequest();
                    request.response().end(res);
                })
                .doOnError(err -> {
                    HttpServerRequest request = MyContext.get().getRequest();
                    err.printStackTrace();
                    request.response().end(err.getMessage());
                })
                .subscribe();
        }).listen(8080);
    }

    private AtomicInteger counter = new AtomicInteger();

    private Single<String> proceed() {
        String message = MyContext.get().getMessage();
        return Single.just(message)
            .map(m -> counter.getAndIncrement() + " " + m);
    }


    public static void main(String[] args) {
        fr.epardaud.reactivecontexts.core.Context.load();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(ExampleVerticle.class.getName());
    }

}
