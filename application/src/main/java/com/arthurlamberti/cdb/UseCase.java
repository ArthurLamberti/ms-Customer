package com.arthurlamberti.cdb;

public abstract class UseCase <IN, OUT> {
    public UseCase() {

    }
    public abstract OUT execute(IN input);
}
