import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;

class SaborGourmet {
    private static List<Cliente> clientes = new ArrayList<>();
    private static Map<String, Double> menu = new LinkedHashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarMenu();
        while (true) {
            System.out.println("Bem-vindo ao Sabor Gourmet!");
            System.out.println("1. Cadastrar-se");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o newline

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    if (loginCliente()) {
                        exibirMenu();
                        realizarPedido();
                    }
                    break;
                case 3:
                    System.out.println("Obrigado por usar o sistema do Sabor Gourmet!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarCliente() {
        System.out.println("== Cadastro de Cliente ==");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente novoCliente = new Cliente(nome, email, endereco, telefone, senha);
        clientes.add(novoCliente);
        System.out.println("Cadastro realizado com sucesso!");
    }

    private static boolean loginCliente() {
        System.out.println("== Login ==");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso! Bem-vindo, " + cliente.getNome());
                return true;
            }
        }
        System.out.println("Email ou senha incorretos.");
        return false;
    }

    private static void inicializarMenu() {
        menu.put("Entrada - Salada Caesar", 15.00);
        menu.put("Prato Principal - Filé Mignon", 40.00);
        menu.put("Sobremesa - Torta de Limão", 12.00);
        menu.put("Bebida - Suco Natural", 8.00);
    }

    private static void exibirMenu() {
        System.out.println("== Menu ==");
        for (Map.Entry<String, Double> item : menu.entrySet()) {
            System.out.println(item.getKey() + ": R$ " + item.getValue());
        }
    }

    private static void realizarPedido() {
        List<String> carrinho = new ArrayList<>();
        double total = 0.0;

        while (true) {
            System.out.print("Digite o nome do item que deseja adicionar ao carrinho (ou 'finalizar' para concluir): ");
            String itemEscolhido = scanner.nextLine();

            if (itemEscolhido.equalsIgnoreCase("finalizar")) {
                break;
            }

            if (menu.containsKey(itemEscolhido)) {
                carrinho.add(itemEscolhido);
                total += menu.get(itemEscolhido);
                System.out.println(itemEscolhido + " adicionado ao carrinho.");
            } else {
                System.out.println("Item não encontrado no menu.");
            }
        }

        if (!carrinho.isEmpty()) {
            System.out.println("== Seu Carrinho ==");
            for (String item : carrinho) {
                System.out.println(item + " - R$ " + menu.get(item));
            }
            System.out.println("Total: R$ " + total);
            processarPagamento(total);
        } else {
            System.out.println("Carrinho vazio. Pedido não realizado.");
        }
    }

    private static void processarPagamento(double total) {
        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Cartão de Débito");
        System.out.println("3. Carteira Digital");
        System.out.print("Opção: ");
        int opcaoPagamento = scanner.nextInt();
        scanner.nextLine(); // Consumir o newline

        switch (opcaoPagamento) {
            case 1:
            case 2:
            case 3:
                System.out.println("Pagamento de R$ " + total + " processado com sucesso.");
                System.out.println("Pedido realizado! Acompanhe o status pelo app.");
                break;
            default:
                System.out.println("Opção de pagamento inválida.");
        }
    }
}
