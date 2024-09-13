package com.SaborGourmet;

import java.text.DecimalFormat;
import java.util.*;


public class Carrinho {

    private List<Item> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        if (item != null) {
            itens.add(item);
        }
    }


    public void removerItem(Item item) {
        if (item != null) {
            itens.remove(item);
        }
    }

    public List<Item> getItens() {
        return itens;
    }

    public void listarItens() {
        itens.sort(Comparator.comparing(Item::getTipo));
        System.out.println("== Seu Carrinho ==");
        for (Item item : itens) {
            System.out.println(item + " - Observacao: " + item.getObservacao());
        }
        System.out.printf("Total = R$ %.2f%n" ,calcularTotal());
    }


    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        return total;
    }

    public void limparCarrinho() {
        itens.clear();
    }


}
