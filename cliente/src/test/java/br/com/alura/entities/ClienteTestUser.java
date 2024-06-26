package br.com.alura.entities;

import br.com.alura.services.ClienteService;
import br.com.alura.utils.ExceptionMessage;

import java.util.Scanner;

public class ClienteTestUser {

    private final static ClienteService clienteService = new ClienteService();
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
                        listarNomes();
                        break;
                    case 3:
                        salvar();
                        break;
                    case 4:
                        deletar();
                        break;
                    case 5:
                        deletarLogico();
                        break;
                    case 6:
                        atualizar();
                        break;
                    case 7:
                        listarPorId();
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
                1 - Listar clientes
                2 - Listar clientes por nome
                3 - Criar um cliente
                4 - Deletar um cliente
                5 - Deletar logicamente um cliente
                6 - Atualizar um cliente
                7 - Listar clientes por id
                9 - Sair
                """);
        return sc.nextInt();
    }

    private static void salvar(){
        System.out.println("Informe o nome do cliente: ");
        String nome = sc.next();
        System.out.println("Informe o cpf do cliente: ");
        String cpf = sc.next();
        System.out.println("Informe o email do cliente: ");
        String email = sc.next();
        Cliente cliente = new Cliente(nome, cpf, email);
        try{
            clienteService.criar(cliente);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Cadastro realizado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void listar(){
        var cliente = clienteService.listar();
        cliente.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void listarNomes() {
        System.out.println("Informe o nome a ser pesquisado:");
        String nome = sc.next();
        var cliente = clienteService.listarPorNome(nome);
        cliente.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void listarPorId() {
        System.out.println("Informe o id do cliente: ");
        Long id = sc.nextLong();
        var cliente = clienteService.listarPorId(id);
        System.out.println(cliente);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void atualizar(){
        System.out.println("Informe o id do cliente: ");
        Long id = sc.nextLong();
        System.out.println("Informe o nome do cliente: ");
        String nome = sc.next();
        System.out.println("Informe o cpf do cliente: ");
        String cpf = sc.next();
        System.out.println("Informe o email do cliente: ");
        String email = sc.next();
        Cliente cliente = new Cliente(nome, cpf, email);
        clienteService.atualizar(id, cliente);

        System.out.println("Atualização realizada com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void deletar() {
        System.out.println("Informe o id do cliente: ");
        Long id = sc.nextLong();
        clienteService.deletar(id);

        System.out.println("Cliente deletado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void deletarLogico(){
        System.out.println("Informe o id do cliente: ");
        Long id = sc.nextLong();
        clienteService.deletarLogico(id);

        System.out.println("Categoria deletada com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }
}
