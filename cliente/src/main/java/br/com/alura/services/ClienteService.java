package br.com.alura.services;

import br.com.alura.DAOs.JpaClienteDAO;
import br.com.alura.entities.Cliente;
import br.com.alura.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteService {

    private final EntityManager em = JPAUtil.getEntityManager();

    public void criar(Cliente cliente){
        new JpaClienteDAO(em).criar(cliente);
    }

    public List<Cliente> listar(){
        return new JpaClienteDAO(em).listarTodos();
    }

    public Cliente listarPorId(Long id){
        return new JpaClienteDAO(em).pesquisaPorId(id);
    }

    public List<Cliente> listarPorNome(String nome){
        return new JpaClienteDAO(em).listarPorNome(nome);
    }

    public void atualizar(Long id, Cliente dto){
        new JpaClienteDAO(em).alterar(id, dto);
    }

    public void deletar(Long id){
        new JpaClienteDAO(em).excluir(id);
    }

    public void deletarLogico(Long id){
        new JpaClienteDAO(em).excluirLogico(id);
    }
}
