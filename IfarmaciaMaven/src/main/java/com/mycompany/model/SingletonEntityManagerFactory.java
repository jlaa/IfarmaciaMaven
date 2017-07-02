/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LucasPC
 */
public class SingletonEntityManagerFactory 
{

   //create an object of SingleObject
   private static final EntityManagerFactory instance =  Persistence.createEntityManagerFactory("com.mycompany_IfarmaciaMaven_war_1.0-SNAPSHOTPU");

   //make the constructor private so that this class cannot be
   //instantiated
   private SingletonEntityManagerFactory(){}

   //Get the only object available
   public static EntityManagerFactory getInstance(){
      return instance;
   }    
}
