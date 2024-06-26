package br.com.alura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate data = LocalDate.now();
    @Column
    private BigDecimal desconto = BigDecimal.ZERO;
    @Column
    private TipoDeDescontoEnum tipoDesconto;
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<ItemDePedido> itens = new ArrayList<>();

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.tipoDesconto = TipoDeDescontoEnum.NENHUM;
    }

    public void adicionarItem(ItemDePedido item) {
        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getTotal());
        desconto(item);
    }

    public void desconto(ItemDePedido item) {
        if(item.getDesconto().compareTo(BigDecimal.ZERO) > 0){
            setDesconto(this.desconto.add(item.getDesconto()));
            if(item.getDesconto().compareTo(new BigDecimal(100)) >= 0) {
                //Esse TipoDeDesconto FIDELIDADE poderia ser atribuído
                //caso esse cliente já tivesse feito outras compras anteriormente
                this.tipoDesconto = TipoDeDescontoEnum.FIDELIDADE;
            } else{
                this.tipoDesconto = TipoDeDescontoEnum.NENHUM;
            }
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", data=" + data +
                ", desconto=" + desconto +
                ", tipoDesconto=" + tipoDesconto +
                ", valorTotal=" + valorTotal +
                ", cliente=" + cliente +
                ", itens=" + itensSummary() +
                '}';
    }

    private String itensSummary() {
        if (itens == null) {
            return "[]";
        }
        return itens.stream()
                .map(item -> "ItemDePedido{id=" + item.getPrecoUnitario() +
                        ", produtoNome='" + item.getProduto().getNome() +
                        "', quantidade=" + item.getQuantidade() +
                        "', desconto=" + item.getDesconto() + "}")
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
