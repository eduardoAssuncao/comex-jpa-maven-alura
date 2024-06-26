package br.com.alura.entities;

import br.com.alura.services.ClienteService;
import br.com.alura.services.PedidoService;
import br.com.alura.services.ProdutoService;
import br.com.alura.utils.ExceptionMessage;

import java.time.LocalDate;
import java.util.Scanner;

public class PedidoTest {

    private final static PedidoService pedidoService = new PedidoService();
    private final static ClienteService clienteService = new ClienteService();
    private final static ProdutoService produtoService = new ProdutoService();
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    public static void main(String[] args) {
        System.out.println("Bom dia! Tudo bem? Espero que sim");
        var opcao = exibirMenu();
        while (opcao != 9) {
            try {
                switch (opcao) {
                    case 1:
                        listar();
                        break;
                    case 2:
                        salvar();
                        break;
                    case 3:
                        listarPorId();
                        break;
                    case 4:
                        listarPorData();
                        break;
                    case 5:
                        adicionarMaisItens();
                        break;
                }
            } catch (ExceptionMessage e) {
                System.out.println("Erro: " + e.getMessage());

                System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
                sc.next();
            }
            opcao = exibirMenu();
        }

        System.out.println("Finalizando a aplicação.");
    }

    private static int exibirMenu() {
        System.out.println("""
                COMEX - ESCOLHA UMA OPÇÃO:
                1 - Listar pedidos
                2 - Criar um pedido
                3 - Listar pedidos por id
                4 - Listar pedidos por data
                5 - Adicionar item ao pedido
                9 - Sair
                """);
        return sc.nextInt();
    }

    private static void salvar(){

        adicionarProdutoAoPedido(criarPedido());

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static Pedido criarPedido(){
        System.out.println("Informe o seu id do cliente:");
        Long idCliente = sc.nextLong();
        var cliente = clienteService.listarPorId(idCliente);
        System.out.println(cliente);
        System.out.println("O cliente selecionado está correto? S ou N");
        String confirmacaoCliente = sc.next();
        while (confirmacaoCliente.equals("N".toLowerCase())) {
            System.out.println("Informe o seu id do cliente:");
            idCliente = sc.nextLong();
            cliente = clienteService.listarPorId(idCliente);
            System.out.println(cliente);
            System.out.println("O cliente selecionado está correto? S ou N");
            confirmacaoCliente = sc.next();
        }

        var pedido = new Pedido(cliente);
        pedidoService.cadastrar(pedido);
        System.out.println("Cliente associado ao pedido");
        return pedido;
    }

    private static void adicionarProdutoAoPedido(Pedido pedidoCriado){
        var produtos = produtoService.listar();
        produtos.stream().forEach(System.out::println);
        System.out.println("Informe o id do Produto que deseja adionar ao pedido: ");
        Long idProduto = sc.nextLong();
        var produto = produtoService.listarPorId(idProduto);
        System.out.println(produto);
        System.out.println("O produto selecionado está correto? S ou N");
        String confirmacaoProduto = sc.next();
        while (confirmacaoProduto.equals("N".toLowerCase())) {
            System.out.println("Informe o id do produto:");
            idProduto = sc.nextLong();
            produto = produtoService.listarPorId(idProduto);
            System.out.println(produto);
            System.out.println("O cliente selecionado está correto? S ou N");
            confirmacaoProduto = sc.next();
        }
        System.out.println("Produto selecionado.");
        System.out.println("\n\n");

        System.out.println("Informe a quantidade do pedido selecionado: ");
        int quantidade = sc.nextInt();
        var itemPedido = new ItemDePedido(quantidade, pedidoCriado, produto);
        pedidoCriado.adicionarItem(itemPedido);

        System.out.println("Produto associado.");
        System.out.println("Pedido realizado com sucesso.");
    }

    private static void adicionarMaisItens(){
        var pedidos = pedidoService.buscarTodos();
        pedidos.stream().forEach(System.out::println);
        System.out.println("Informe o id do seu pedido:");
        Long idPedido = sc.nextLong();
        var pedido = pedidoService.buscarPorId(idPedido);
        System.out.println(pedido);
        System.out.println("O pedido selecionado está correto? S ou N");
        String confirmacaoPedido = sc.next();
        while (confirmacaoPedido.equals("N")) {
            System.out.println("Informe o id do pedido:");
            idPedido = sc.nextLong();
            pedido = pedidoService.buscarPorId(idPedido);
            System.out.println(pedido);
            System.out.println("O cliente selecionado está correto? S ou N");
            confirmacaoPedido = sc.next();
        }
        System.out.println("Pedido selecionado.");
        adicionarProdutoAoPedido(pedido);
    }

    private static void listar(){
        var pedido = pedidoService.buscarTodos();
        pedido.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void listarPorId() {
        System.out.println("Informe o id do pedido: ");
        Long id = sc.nextLong();
        var pedido = pedidoService.buscarPorId(id);
        System.out.println(pedido);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void listarPorData(){
        System.out.println("Informe a data aaaa-mm-dd: ");
        String data = sc.next();
        var date = LocalDate.parse(data);
        var pedido = pedidoService.buscarPorData(date);
        pedido.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }
}
