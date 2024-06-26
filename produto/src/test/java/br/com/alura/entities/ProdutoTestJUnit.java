package br.com.alura.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTestJUnit {
    private Produto produto;

    @BeforeEach
    public void setup() {
        produto = new Produto("Produto Teste", "Descrição do Produto", BigDecimal.TEN);
    }

    @Test
    public void testAdicionarCategoria() {
        Categoria categoria1 = new Categoria("Eletrônicos", "Eletrônicos");
        Categoria categoria2 = new Categoria("Informática", "Informaticas");

        produto.getCategorias().add(categoria1);
        produto.getCategorias().add(categoria2);

        assertEquals(2, produto.getCategorias().size());
    }
}