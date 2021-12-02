/**
 * 
 */
package com.jpademo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author admi
 *
 */
public class MemberMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Member member = new Member();
		member.setMemberName("Vivek");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = emf.createEntityManager();
		
		//Insert Data into a Database
		
		em.getTransaction().begin();
		em.persist(member);
		
		em.getTransaction().commit();
		
		/**
		//Retrieve book by an Id 
		em.getTransaction().begin();
		Book bk =  em.find(Book.class,11);
		System.out.println(bk);
		em.getTransaction().commit();
*/
	}

}
