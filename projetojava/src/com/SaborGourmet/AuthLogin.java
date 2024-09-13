package com.SaborGourmet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthLogin {



    public boolean login(String email, String senha, Cliente cliente){

        if (email.equals(cliente.getEmail()) && senha.equals(cliente.getSenha())){
            return true;
        }
        return false;
    }

    public Cliente cadastro (List<Cliente> clientes) {
        Scanner x = new Scanner(System.in);

        System.out.println("== Cadastro de Cliente ==");
        System.out.print("Nome: ");
        String nome = x.nextLine();
        String email;
        while (true) {
            System.out.print("Email: ");
            email = x.nextLine();
            if (isEmailUnico(clientes, email)) {
                break;
            } else {
                System.out.println("Email já cadastrado. Tente novamente.");
            }
        }

        System.out.print("Endereço: ");
        String endereco = x.nextLine();
        System.out.print("Telefone: ");
        String telefone = x.nextLine();
        System.out.print("Senha: ");
        String senha = x.nextLine();
        return new Cliente(nome, email, endereco, telefone, senha);
    }

    private boolean isEmailUnico(List<Cliente> clientes, String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

}
