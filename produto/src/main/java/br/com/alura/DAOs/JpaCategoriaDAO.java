package br.com.alura.DAOs;

import br.com.alura.utils.ExceptionMessage;
import br.com.alura.entities.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaCategoriaDAO {

    private final EntityManager em;

    public JpaCategoriaDAO(EntityManager em) { this.em = em; }

    public void criar(Categoria categoria){
        em.getTransaction().begin();
        em.persist(categoria);
        em.getTransaction().commit();
    }

    public List<Categoria> listarTodos(){
        String jpql = "SELECT c FROM Categoria c WHERE c.ativo = true ORDER BY c.nome";
        return em.createQuery(jpql, Categoria.class).getResultList();
    }

    public Categoria pesquisarPorId(Long id){
        var categoria = em.find(Categoria.class, id);
        if(categoria == null){
            throw new ExceptionMessage("Categoria não encontrada!");
        }
        return categoria;
    }

    public List<Categoria> listarNomes(String nome){
        String jpql = "SELECT c FROM Categoria c WHERE c.ativo = true and c.nome LIKE :nome ORDER BY c.nome ASC";
        return em.createQuery(jpql, Categoria.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
    }

    public void alterar(Long id, Categoria categoria){
        var cat = em.find(Categoria.class, id);
        em.getTransaction().begin();
        cat.setNome(categoria.getNome());
        cat.setDescricao(categoria.getDescricao());
        em.getTransaction().commit();
    }

    public void excluir(Long id){
        var cat = em.find(Categoria.class, id);
        em.getTransaction().begin();
        cat = em.merge(cat);
        em.remove(cat);
        em.getTransaction().commit();
    }

    public void excluirLogico(Long id){
        var cat = em.find(Categoria.class, id);
        em.getTransaction().begin();
        cat.setAtivo(false);
        em.getTransaction().commit();
    }
}
