package br.com.alura.dto;

import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;

import javax.persistence.EntityManager;

//Antes de cadastrar um Produto, é preciso "persistir/cadastrar" uma Categoria no banco, pois, é preciso passar
//a Categoria daquele produto no momento do seu cadastro
//Devido a isso foi criada essa classe com o método de cadastrar Categoria
public class CategoriaDto {

    private EntityManager entityManager;

    //Ao instanciar o Objeto CategoriaDto, necessário passar como parâmetro seu EntityManager
    public CategoriaDto(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Inserindo uma Categoria no Banco de Dados
    public void cadastrar (Categoria categoria){
        this.entityManager.persist(categoria);
    }

    //Atualizando uma Categoria no Banco de Dados
    public void atualizar (Categoria categoria){
        this.entityManager.merge(categoria);
    }

    //Removendo uma Categoria no Banco de Dados
    public void remover (Categoria categoria){
        categoria = entityManager.merge(categoria);
        this.entityManager.remove(categoria);
    }
}
