package br.com.alura.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTestJUnit {

    private Cliente cliente;
    private Pedido pedido;

    @BeforeEach
    public void setup() {
        cliente = new Cliente("Fulano", "123.456.789-00", "fulano@example.com");
        pedido = new Pedido(cliente);
    }

    @Test
    public void testAdicionarItemAoPedido() {
        Produto produto = new Produto("Produto Teste", "Descricao", BigDecimal.TEN);
        ItemDePedido item = new ItemDePedido(1, pedido, produto);

        pedido.adicionarItem(item);

        assertEquals(1, pedido.getItens().size());
        assertEquals(BigDecimal.TEN, pedido.getValorTotal());
    }

}