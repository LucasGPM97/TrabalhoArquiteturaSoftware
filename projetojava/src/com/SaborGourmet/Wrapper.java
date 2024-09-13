package com.SaborGourmet;

/*
* Classe para instanciar o usuario ativo no sistema.
*
* */
public class Wrapper<T> {
    private T value;

    public Wrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
