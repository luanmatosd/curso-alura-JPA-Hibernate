package br.com.alura.modelo;
//Mapeamento do mundo OO do Java com o mundo relacional do Banco de dados
//Mapeando uma entidade JPA. Assim que representamos quais classes vão representar tabelas no Banco de dados
//Funciona como um espelho do banco de dados

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity //JPA, está vendo essa classe Produto? Ela é uma entidade, ou seja, existe uma tabela no banco de dados que está mapeando, e que é o espelho dessa classe.
@Table(name = "produtos")//O nome da Classe precisa ser igual no banco de dados. Table é usado para indicar isso
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "desc")//Nome da coluna precisa ser igual a do atributo. Essa anotação nos ajuda nisso
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataCadastro = LocalDate.now();
    @ManyToOne //Traduzindo: Muitos Produtos estão vinculados a uma Categoria
    private Categoria categoria; //Um produto pode ter uma categoria. Uma categoria pode estar vinculada a vários Produtos.

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    //Construtor Default que a JPA obriga para rodar
    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
