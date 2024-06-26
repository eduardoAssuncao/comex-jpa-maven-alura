package br.com.alura.entities;

import br.com.alura.services.CategoriaService;
import br.com.alura.utils.ExceptionMessage;

import java.util.Scanner;

public class CategoriaTest {
    private final static CategoriaService categoriaService = new CategoriaService();
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
                1 - Listar categorias
                2 - Listar categorias por nome
                3 - Criar um categoria
                4 - Deletar um categoria
                5 - Deletar logicamente uma categoria
                6 - Atualizar uma categoria
                7 - Listar categorias por id
                9 - Sair
                """);
        return sc.nextInt();
    }

    private static void salvar() {
        System.out.println("Informe o nome da categoria: ");
        String nome = sc.next();
        System.out.println("Informe a descrição da categoria: ");
        String descricao = sc.next();
        Categoria categoria = new Categoria(nome, descricao);
        categoriaService.criar(categoria);

        System.out.println("Cadastro realizado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void listar() {
        var categoria = categoriaService.listar();
        categoria.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void listarNomes() {
        System.out.println("Informe o nome da categoria:");
        String nome = sc.next();
        var categoria = categoriaService.listarNomes(nome);
        categoria.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void listarPorId() {
        System.out.println("Informe o id da categoria: ");
        Long id = sc.nextLong();
        var categoria = categoriaService.listarPorId(id);
        System.out.println(categoria);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void atualizar() {
        System.out.println("Informe o id do categoria: ");
        Long id = sc.nextLong();
        System.out.println("Informe o nome do categoria: ");
        String nome = sc.next();
        System.out.println("Informe a descrição da categoria: ");
        String descricao = sc.next();
        Categoria categoria = new Categoria(nome, descricao);
        categoriaService.atualizar(id, categoria);

        System.out.println("Atualização realizada com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void deletar() {
        System.out.println("Informe o id da categoria: ");
        Long id = sc.nextLong();
        categoriaService.deletar(id);

        System.out.println("Categoria deletada com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }

    private static void deletarLogico() {
        System.out.println("Informe o id da categoria: ");
        Long id = sc.nextLong();
        categoriaService.deletarLogico(id);

        System.out.println("Categoria deletada com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }
}
