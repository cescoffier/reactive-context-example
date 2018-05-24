package io.escoffier.demo;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class MyContext {

    private static ThreadLocal<MyState> storage = new ThreadLocal<>();

    static void init() {
        storage.set(new MyState());
    }

    public static MyState get() {
        return storage.get();
    }

    public static MyState set(MyState newState) {
        MyState old = storage.get();
        storage.set(newState);
        return old;
    }
}
