package com.galt.library.core.model.fx;

/**
 * Created by Grishechko on 26.11.2015.
 */
public abstract class FxBase<T> {
    FxBase(T object) {
        update(object);
    }
    abstract void update(T object);
}
