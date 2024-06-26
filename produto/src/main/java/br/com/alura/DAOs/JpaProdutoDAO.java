package br.com.alura.DAOs;

import br.com.alura.entities.Produto;
import br.com.alura.utils.ExceptionMessage;

import javax.persistence.EntityManager;
import java.util.*;

public class JpaProdutoDAO {

    private final EntityManager em;

    public JpaProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void criar(Produto produto){
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public List<Produto> listarTodos(){
        String jpql = "SELECT p FROM Produto p " +
                "WHERE p.ativo = true ORDER BY p.nome ASC";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public Produto pesquisarPorId(Long id){
        var produto = em.find(Produto.class, id);
        if(produto == null){
            throw new ExceptionMessage("Produto não encontrado!");
        }
        return produto;
    }

    public List<Produto> listarNomes(String nome){
        String jpql = "SELECT p FROM Produto p " +
                "WHERE p.ativo = true " +
                "AND p.nome LIKE :nome ORDER BY p.nome ASC";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
    }

    public List<Produto> listarPorCategoria(String categoria){
        String jpql = "SELECT p FROM Produto p " +
                "JOIN p.categorias c " +
                "WHERE p.ativo = true " +
                "AND c.nome LIKE :categoria ORDER BY p.nome ASC";
        return em.createQuery(jpql, Produto.class)
                .setParameter("categoria", "%"+categoria+"%")
                .getResultList();
    }

    public void alterar(Long id, Produto produto){
        var prod = em.find(Produto.class, id);
        em.getTransaction().begin();
        prod.setNome(produto.getNome());
        prod.setDescricao(produto.getDescricao());
        prod.setValor(produto.getValor());
        em.getTransaction().commit();
    }

    public void excluir(Long id){
        var prod = em.find(Produto.class, id);
        em.getTransaction().begin();
        prod = em.merge(prod);
        em.remove(prod);
        em.getTransaction().commit();
    }

    public void excluirLogico(Long id){
        var prod = em.find(Produto.class, id);
        em.getTransaction().begin();
        prod.setAtivo(false);
        em.getTransaction().commit();
    }
}
