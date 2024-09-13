package com.SaborGourmet;

import java.text.DecimalFormat;

public class Item{
    private String descricao;
    private Float preco;
    private String tipo; // entrada, prato principal, sobremesa ou bebida
    private String observacao; // informar ponto da carne, retirar algo etc.


    public Item() {
    }

    public Item(String descricao, Float preco, String tipo, String observacao) {
        this.descricao = descricao;
        this.preco = preco;
        this.tipo = tipo;
        this.observacao = observacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservacao() {
        return observacao;
    }


    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return  tipo +
                " - " + descricao +
                " - R$ " + preco;
    }

}
