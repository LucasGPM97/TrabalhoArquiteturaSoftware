import com.SaborGourmet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main
 */
public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static Wrapper<Cliente> usuarioAtivo = new Wrapper<>(null);


    public static void main(String[] args) {
        AuthLogin authlogin = new AuthLogin();
        Menu menu = new Menu();
        iniciarMenu(menu);
        while (true) {

            int opcao = telaInicial();
            switch (opcao) {
                case 1:
                    Cliente newCliente = authlogin.cadastro(clientes);
                    clientes.add(newCliente);
                    break;
                case 2:
                    if(login(authlogin,clientes,usuarioAtivo)){
                        estruturaTelaCliente(usuarioAtivo.getValue(), menu);
                    }
                    break;
                case 0:
                    System.out.println("Obrigado por usar o sistema do Sabor Gourmet!");
                    return;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }

    }


    private static boolean login(AuthLogin authLogin, List<Cliente> clientes, Wrapper<Cliente> usuarioAtivo){
        Scanner x = new Scanner(System.in);
        System.out.println("== Login ==");
        System.out.println("Email: ");
        String email = scannerString();
        System.out.println("Senha: ");
        String senha = scannerString();


        boolean loginSucess = false;
        for (Cliente cliente : clientes){
            if (authLogin.login(email,senha,cliente)){
                loginSucess = true;
                usuarioAtivo.setValue(cliente);
                break;
            }
        }

        if (loginSucess){
            System.out.println("Login realizado com sucesso!");

        }else{
            System.out.println("Email ou senha incorretos.");
        }

        return loginSucess;

    }

    private static void estruturaTelaCliente(Cliente usuarioAtivo, Menu menu){

       while (true) {
           telaCliente(usuarioAtivo);
           int opcao = escolha();
           switch (opcao) {
               case 1:
                   telaMenu(menu);
                   break;
               case 2:
                   adicionarItemAoCarrinho(usuarioAtivo, menu);
                   break;
               case 3:
                   if (usuarioAtivo.getCarrinho().getItens().isEmpty()) {
                       System.out.println("Carrinho vazio!");
                   }else {
                       visualizarCarrinho(usuarioAtivo);
                   }
                   break;
               case 4:
                   if (usuarioAtivo.getCarrinho().getItens().isEmpty()) {
                       System.out.println("Carrinho vazio!");
                   }else{
                       finalizarPedido(usuarioAtivo);
                   }
                   break;
               case 5:
                   if (usuarioAtivo.getPedido().getOpcaoDePagamento() != null) {
                       detalhesPedido(usuarioAtivo);
                   }else
                       System.out.println("Nenhum pedido encontrado");
                   break;
               case 0:
                   return;
               default:
                   System.out.println("Opcao invalida");
           }
       }
    }

    private static void visualizarCarrinho(Cliente usuarioAtivo) {
        usuarioAtivo.getCarrinho().listarItens();
    }

    private static void finalizarPedido(Cliente usuarioAtivo)  {
        usuarioAtivo.getPedido().addPedido(usuarioAtivo.getCarrinho());
        formasPagamento();
        adicionarFormaPgto(usuarioAtivo);
        usuarioAtivo.getPedido().statusAutomatico("Recebido");
        detalhesPedido(usuarioAtivo);
        usuarioAtivo.getCarrinho().limparCarrinho();

    }

    private static void formasPagamento(){
        System.out.println();
        System.out.println("== Forma de Pagamento ==");
        System.out.println("[1] Cartão de crédito");
        System.out.println("[2] Cartão de Débito");
        System.out.println("[3] Carteira digital");
    }

    private static void detalhesPedido(Cliente usuarioAtivo){
        System.out.println();
        System.out.println("== Detalhes do Pedido ==");
        System.out.println(usuarioAtivo.getPedido());
        usuarioAtivo.getPedido().listarItensComprados();
        System.out.println("Status do Pedido: " + usuarioAtivo.getPedido().getStatus());
    }

    private static void adicionarFormaPgto(Cliente usuarioAtivo) {
        int opcao = escolha();
        switch (opcao){
            case 1:
                usuarioAtivo.getPedido().setOpcaoDePagamento("Cartão de crédito");
                break;
            case 2:
                usuarioAtivo.getPedido().setOpcaoDePagamento("Cartão de Débito");
                break;
            case 3:
                usuarioAtivo.getPedido().setOpcaoDePagamento("Carteira Digital");
                break;
            default:
                System.out.println("Opção inválida. Forma de pagamento não selecionada.");
                break;
        }


    }

    private static void adicionarItemAoCarrinho(Cliente usuarioAtivo, Menu menu) {
        System.out.println("== Adicionar ao Carrinho ==");
        menu.listarItens();

        int indice = escolha();
        List<Item> itens = menu.getItens();

        if (indice >= 0 && indice < itens.size()) {
            Item item = itens.get(indice);

            if (adicionarObservacao() == 1){
                System.out.println("Observação: ");
                item.setObservacao(scannerString());
            }

            usuarioAtivo.getCarrinho().adicionarItem(item);
            System.out.println("Item adicionado ao carrinho: " + item + " - Observacao: " + item.getObservacao());

        } else {
            System.out.println("Índice inválido.");
        }
    }

    private static int adicionarObservacao(){
        System.out.println("Deseja adicionar uma observacao? [0] - Nao [1] = Sim");
        return escolha();

    }

    private static void telaMenu(Menu menu){
        System.out.println("== Menu ==");
        menu.listarItens();
    }
    
    private static void telaCliente(Cliente usuarioAtivo){
        System.out.println();
        System.out.println("Ola! " + usuarioAtivo.getNome() + "!");
        System.out.println("[1] Visualizar Menu");
        System.out.println("[2] Adicionar item ao carrinho");
        System.out.println("[3] Visualizar carrinho");
        System.out.println("[4] Finalizar pedido");
        System.out.println("[5] Acompanhar Status");
        System.out.println("[0] Sair");
    }

    private static int telaInicial() {
        System.out.println();
        System.out.println("Bem vindo ao Sabor Gourmet! ");
        System.out.println("[1] Cadastrar-se");
        System.out.println("[2] Login");
        System.out.println("[0] Sair");
        return escolha();
    }

    private static int escolha() {
        Scanner x = new Scanner(System.in);
        System.out.println();
        System.out.printf("Selecione uma opcao: ");

        while (!x.hasNextInt()) {
            System.out.println("Erro. Digite um numero inteiro: ");
            x.next();
        }

        int escolha = x.nextInt();
        return escolha;
    }

    private static String scannerString(){
        Scanner x = new Scanner(System.in);
        return x.nextLine();
    }

    private static void iniciarMenu(Menu menu){
        menu.adicionarItem(new Item("Hamburguer", 15.50f, "prato principal", "N/A"));
        menu.adicionarItem(new Item("Batata Frita", 10f, "entrada", "N/A"));
        menu.adicionarItem(new Item("Coca Cola", 8.90f, "bebida", "N/A"));
        menu.adicionarItem(new Item("Suco Natural", 5.90f, "bebida", "N/A"));
        menu.adicionarItem(new Item("Sorvete", 3.50f, "sobremesa", "N/A"));
        menu.adicionarItem(new Item("Pizza", 42.50f, "prato principal", "N/A"));

    }

}

