package br.com.alura.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    @Test
    public void testGetterAndSetter() {
        Cliente cliente = new Cliente("Fulano", "12345678900", "fulano@example.com");

        Assertions.assertEquals("Fulano", cliente.getNome());
        Assertions.assertEquals("12345678900", cliente.getCpf());
        Assertions.assertEquals("fulano@example.com", cliente.getEmail());
        Assertions.assertTrue(cliente.getAtivo());

        cliente.setNome("Ciclano");
        cliente.setCpf("98765432100");
        cliente.setEmail("ciclano@example.com");
        cliente.setAtivo(false);

        Assertions.assertEquals("Ciclano", cliente.getNome());
        Assertions.assertEquals("98765432100", cliente.getCpf());
        Assertions.assertEquals("ciclano@example.com", cliente.getEmail());
        Assertions.assertFalse(cliente.getAtivo());
    }
}