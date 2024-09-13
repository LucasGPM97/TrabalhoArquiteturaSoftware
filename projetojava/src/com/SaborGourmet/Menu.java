package com.SaborGourmet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Menu {
    private List<Item> itens;

    public Menu(){
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        if (item != null){
            itens.add(item);
        }else {
            System.out.println("O Item nao pode ser nulo");
        }
    }

    public void listarItens() {
        itens.sort(Comparator.comparing(Item::getTipo));
        int index = 0;
        for (Item item : itens) {
            System.out.println("[" + index + "] - " + item);
            index++;
        }
    }


    public Item buscarItemPorTipo(String tipo) {
        for (Item item : itens) {
            if (item.getTipo().equalsIgnoreCase(tipo)) {
                return item;
            }
        }
        return null;
    }

    public List<Item> getItens() {
        return itens;
    }

}
