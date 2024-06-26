package br.com.alura.services;

import br.com.alura.DAOs.JpaProdutoDAO;
import br.com.alura.entities.Produto;
import br.com.alura.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoService {

    private final EntityManager em = JPAUtil.getEntityManager();

    public void criar(Produto produto){
        new JpaProdutoDAO(em).criar(produto);
    }

    public List<Produto> listar(){
        return new JpaProdutoDAO(em).listarTodos();
    }

    public List<Produto> listarNomes(String nome){
        return new JpaProdutoDAO(em).listarNomes(nome);
    }

    public List<Produto> listarPorCategoria(String categoria){ return new JpaProdutoDAO(em).listarPorCategoria(categoria); }

    public Produto listarPorId(Long id) { return new JpaProdutoDAO(em).pesquisarPorId(id); }

    public void atualizar(Long id, Produto produto){
        new JpaProdutoDAO(em).alterar(id, produto);
    }

    public void deletar(Long id){
        new JpaProdutoDAO(em).excluir(id);
    }

    public void deletarLogico(Long id){
        new JpaProdutoDAO(em).excluirLogico(id);
    }
}
