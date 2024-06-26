package br.com.alura.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "itens_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
    @Column
    private int quantidade;
    @Column
    private BigDecimal desconto = BigDecimal.ZERO;
    @Column
    private TipoDescontoProdutoEnum tipoDesconto;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    public ItemDePedido() {}

    public ItemDePedido(int quantidade, Pedido pedido, Produto produto) {
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.precoUnitario = produto.getValor();
        this.produto = produto;

        if(quantidade >= 2){
            this.tipoDesconto = TipoDescontoProdutoEnum.QUANTIDADE;
        } else {
            this.tipoDesconto = TipoDescontoProdutoEnum.NENHUM;
        }
    }

    public BigDecimal getValor() {
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }

    public BigDecimal getTotal() {
        return getValor().subtract(desconto());
    }

    private BigDecimal desconto() {
        BigDecimal desconto = BigDecimal.ZERO;
        if(this.tipoDesconto.equals(TipoDescontoProdutoEnum.QUANTIDADE)){
            desconto = this.produto.getValor().divide(new BigDecimal(this.quantidade));
            this.desconto = this.desconto.add(desconto);
        }
        return desconto;
    }

    public void promocao(){
        this.tipoDesconto = TipoDescontoProdutoEnum.PROMOCAO;
    }
}
