package com.SaborGourmet;
import java.util.Scanner;

public class Cliente {

    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String senha;
    private Carrinho carrinho;
    private Pedido pedido;

    public Cliente() {

    }


    public Cliente(String nome, String email, String endereco, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.senha = senha;
        this.carrinho = new Carrinho();
        this.pedido = new Pedido();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
