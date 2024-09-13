package com.SaborGourmet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Pedido {

    private Integer id;
    private Carrinho carrinho;
    private double valorTotal;
    private String opcaoDePagamento;
    private String status;
    private List<Item> itensComprados;

    public Pedido(){
        this.id = 0;
        this.carrinho = new Carrinho();
    }
    public void addPedido(Carrinho carrinho) {
        this.carrinho = carrinho;
        this.valorTotal = carrinho.calcularTotal();
        this.id++;
        this.itensComprados = new ArrayList<>(carrinho.getItens());
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getOpcaoDePagamento() {
        return opcaoDePagamento;
    }

    public void setOpcaoDePagamento(String opcaoDePagamento) {
        this.opcaoDePagamento = opcaoDePagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItensComprados() {
        return itensComprados;
    }

    public void listarItensComprados(){
        itensComprados.sort(Comparator.comparing(Item::getTipo));
        System.out.println("== Seu Carrinho ==");
        for (Item item : itensComprados) {
            System.out.println(item + " - Observacao: " + item.getObservacao());
        }
        System.out.printf("Total = R$ %.2f%n" , getValorTotal() );
    }


    public void statusAutomatico(String status){
        this.status = status;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(() -> setStatus("Em preparação"), 10, TimeUnit.SECONDS);
        scheduler.schedule(() -> setStatus("Pronto para entrega"), 20, TimeUnit.SECONDS);
        scheduler.schedule(() -> setStatus("Em entrega"), 30, TimeUnit.SECONDS);
        scheduler.schedule(() -> setStatus("Entregue"), 40, TimeUnit.SECONDS);

        scheduler.schedule(() -> scheduler.shutdown(), 50, TimeUnit.SECONDS);
    }

    @Override
    public String toString() {
        return  "Pedido:  " + id +
                "\nForma de Pagamento: " + opcaoDePagamento;
    }
}
