package br.com.alura.testes;

import br.com.alura.dto.CategoriaDto;
import br.com.alura.dto.ProdutoDto;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

//Persistindo uma Entidade
public class CadastroProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        buscarProduto();
        buscarTodos();
        buscarPorNome();
        buscarPorCategoria();
        buscarPrecoPeloNome();
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        
        Produto iphone = new Produto("Iphone 15 Pro Max", "Veloz", new BigDecimal("15000"), celulares);
        Produto samsung = new Produto("Samsung S24 Pro", "Rápido", new BigDecimal("6000"), celulares);

        EntityManager manager = JPAUtil.getEntityManager();

        CategoriaDto categoriaDto = new CategoriaDto(manager);
        ProdutoDto produtoDto = new ProdutoDto(manager);

        //Transforma a Entidade Produto em uma registro na tabela de produtos do banco de dados
        manager.getTransaction().begin(); //Iniciando uma nova transação
        categoriaDto.cadastrar(celulares); //Transação "persist" dentro do método "cadastrar"
        produtoDto.cadastrar(iphone);
        produtoDto.cadastrar(samsung);
        manager.getTransaction().commit(); //Sinaliza que as operações da transação foram feitas com sucesso
        manager.close();
    }

    private static void buscarProduto(){
        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDto produtoDto = new ProdutoDto(manager);

        Produto produto = produtoDto.buscarProduto(1l);

        System.out.println("\nBuscando celular pelo ID: ");
        System.out.println(produto.getNome());
    }

    private static void buscarTodos(){
        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDto produtoDto = new ProdutoDto(manager);

        List<Produto> produto = produtoDto.buscarTodos();

        System.out.println("\nBuscando todos os celulares:");
        produto.forEach(p -> System.out.println(p.getNome()));
    }

    private static void buscarPorNome(){
        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDto produtoDto = new ProdutoDto(manager);

        List<Produto> produto = produtoDto.buscarPeloNome("Samsung S24 Pro");

        System.out.println("\nBuscando celular pelo Nome:");
        produto.forEach(p -> System.out.println(p.getNome()));
    }

    private static void buscarPorCategoria(){
        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDto produtoDto = new ProdutoDto(manager);

        List<Produto> produto = produtoDto.buscarPeloCategoria("CELULARES");

        System.out.println("\nBuscando celulares pela Categoria:");
        produto.forEach(p -> System.out.println(p.getNome()));
    }

    private static void buscarPrecoPeloNome(){
        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDto produtoDto = new ProdutoDto(manager);

        BigDecimal produto = produtoDto.buscarPrecoPeloNome("Samsung S24 Pro");

        System.out.println("\nBuscando preço pelo Nome:");
        System.out.println("Preço: " + produto);
    }
}
