package br.com.alura.DAOs;

import br.com.alura.entities.Pedido;
import br.com.alura.utils.ExceptionMessage;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class JpaPedidoDAO {

    private final EntityManager em;

    public JpaPedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
    }

    public Pedido buscarPorId(Long id) {
        return em.find(Pedido.class, id);
    }

    public List<Pedido> buscarPorData(LocalDate data) {
        String jpsql = "SELECT p FROM Pedido p WHERE p.data = :data";
        return em.createQuery(jpsql, Pedido.class)
                .setParameter("data", data)
                .getResultList();
    }

    public List<Pedido> listarTodos() {
        String jpql = "SELECT p FROM Pedido p";
        List<Pedido> pedidos = em.createQuery(jpql, Pedido.class).getResultList();
        if(pedidos.isEmpty()){
            throw new ExceptionMessage("Ainda não há pedidos.");
        }else {
            return pedidos;
        }
    }
}
