package br.com.alura.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Classe criada com o objetivo de gerar uma método estático de criação de Entity Manager
//Entity Manager é necessário, pois, possui os métodos de operar no banco de dados
public class JPAUtil {

    //"loja" = persistence-unit
    //Assim indica a JPA em qual banco ela deve se conectar
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja");

    //Método que faz a ponte do Java com o Banco de dados
    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
