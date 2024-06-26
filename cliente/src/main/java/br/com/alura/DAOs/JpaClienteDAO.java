package br.com.alura.DAOs;

import br.com.alura.entities.Cliente;
import br.com.alura.utils.ExceptionMessage;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaClienteDAO {

    private final EntityManager em;

    public JpaClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void criar(Cliente cliente){
        try{
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        }catch(RuntimeException e){
            throw new ExceptionMessage("Erro ao criar o cliente!");
        }
    }

    public Cliente pesquisaPorId(Long id){
        var cliente = this.em.find(Cliente.class, id);
        if(cliente == null){
            throw new ExceptionMessage("Erro ao encontrar um cliente");
        }
        return cliente;
    }

    public List<Cliente> listarTodos(){
        String jpql = "SELECT c FROM Cliente c WHERE c.ativo = true ORDER BY c.nome";//Não consigo fazer comparação de boolean c.ativo = true
        return em.createQuery(jpql, Cliente.class).getResultList();
    }

    public List<Cliente> listarPorNome(String nome){
        String jpql = "SELECT c FROM Cliente c WHERE c.ativo = true and c.nome like :nome ORDER BY c.nome ASC";
        return em.createQuery(jpql, Cliente.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
    }

    public void alterar(Long id, Cliente cliente){
        var cli = em.find(Cliente.class, id);
        em.getTransaction().begin();
        cli.setNome(cliente.getNome());
        cli.setCpf(cliente.getCpf());
        cli.setEmail(cliente.getEmail());
        em.getTransaction().commit();
    }

    public void excluir(Long id){
        Cliente cliente = pesquisaPorId(id);
        em.getTransaction().begin();
        cliente = em.merge(cliente);
        em.remove(cliente);
        em.getTransaction().commit();
    }

    public void excluirLogico(Long id){
        em.getTransaction().begin();
        var cliente = em.find(Cliente.class, id);
        cliente.setAtivo(false);
        em.getTransaction().commit();
    }
}
