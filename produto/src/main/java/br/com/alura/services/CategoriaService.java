package br.com.alura.services;


import br.com.alura.DAOs.JpaCategoriaDAO;
import br.com.alura.utils.JPAUtil;
import br.com.alura.entities.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaService {

    private final EntityManager em = JPAUtil.getEntityManager();

    public void criar(Categoria categoria) {
        new JpaCategoriaDAO(em).criar(categoria);
    }

    public List<Categoria> listar() {
        return new JpaCategoriaDAO(em).listarTodos();
    }

    public Categoria listarPorId(Long id) {
        return new JpaCategoriaDAO(em).pesquisarPorId(id);
    }

    public List<Categoria> listarNomes(String nome) {
        return new JpaCategoriaDAO(em).listarNomes(nome);
    }

    public void atualizar(Long id, Categoria categoria) {
        new JpaCategoriaDAO(em).alterar(id, categoria);
    }

    public void deletar(Long id) {
        new JpaCategoriaDAO(em).excluir(id);
    }

    public void deletarLogico(Long id) {
        new JpaCategoriaDAO(em).excluirLogico(id);
    }
}
