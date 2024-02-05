package br.com.alura.dto;

import br.com.alura.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

//Classe criada para salvar um ProdutoDto no banco de dados. Não é bom usar a propria Entidade JPA
public class ProdutoDto {

    private EntityManager entityManager;

    //Ao instanciar o Objeto ProdutoDto, necessário passar como parâmetro seu EntityManager
    public ProdutoDto(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Inserindo um Produto no Banco de Dados
    public void cadastrar (Produto produto){
        this.entityManager.persist(produto);
    }

    //Buscando Produto pelo ID
    public Produto buscarProduto (Long id){
        return entityManager.find(Produto.class, id);
    }

    //Buscando todos os Produtos
    public List<Produto> buscarTodos(){
        String jpql = "SELECT p FROM Produto p"; //Comando em JPQL
        return entityManager.createQuery(jpql, Produto.class).getResultList();
    }

    //Buscando produto pelo Nome
    public List<Produto> buscarPeloNome(String nomeProduto){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nomeProduto"; //: indica que é um parâmetro dinâmico na Query
        return entityManager.createQuery(jpql, Produto.class).
                setParameter("nomeProduto", nomeProduto).
                getResultList();
    }

    //Buscando produto pelo Categoria
    public List<Produto> buscarPeloCategoria(String nomeCategoria){ //p.categoria.nome indica um JOIN
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nomeCategoria"; //: indica que é um parâmetro dinâmico na Query
        return entityManager.createQuery(jpql, Produto.class).
                setParameter("nomeCategoria", nomeCategoria).
                getResultList();
    }

    //Buscando preco do Produto pelo Nome
    //Trazendo apenas um atributo da Entidade, e não trazendo todos atributos conforme exemplos acima
    public BigDecimal buscarPrecoPeloNome(String nomeProduto){
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nomeProduto"; //: indica que é um parâmetro dinâmico na Query
        return entityManager.createQuery(jpql, BigDecimal.class).
                setParameter("nomeProduto", nomeProduto).
                getSingleResult();
    }
}
