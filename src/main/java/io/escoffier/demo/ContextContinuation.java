package io.escoffier.demo;

import fr.epardaud.reactivecontexts.core.ContextProvider;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class ContextContinuation implements ContextProvider<MyState> {
    
    @Override
    public MyState install(MyState myState) {
        return MyContext.set(myState);
    }

    @Override
    public void restore(MyState myState) {
        MyContext.set(myState);
    }

    @Override
    public MyState capture() {
        return MyContext.get();
    }
}
