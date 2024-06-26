package br.com.alura.services;

import br.com.alura.DAOs.JpaPedidoDAO;
import br.com.alura.entities.Pedido;
import br.com.alura.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class PedidoService {

    private final EntityManager em = JPAUtil.getEntityManager();

    public void cadastrar(Pedido pedido){
        new JpaPedidoDAO(em).cadastrar(pedido);
    }

    public Pedido buscarPorId(Long id){
        return new JpaPedidoDAO(em).buscarPorId(id);
    }

    public List<Pedido> buscarTodos(){
        return new JpaPedidoDAO(em).listarTodos();
    }

    public List<Pedido> buscarPorData(LocalDate data){
        return new JpaPedidoDAO(em).buscarPorData(data);
    }
}
