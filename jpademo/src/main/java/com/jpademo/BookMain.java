/**
 * 
 */
package com.jpademo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author admi
 *
 */
public class BookMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Book book = new Book();
		//book.setBookId(3);
		book.setBookTitle("An introduction to Java");
		book.setIsbn("11123ehjfs231232J");
		
		Book bookOne = new Book();
		//bookOne.setBookId(4);
		bookOne.setBookTitle("An Introduction to C++");
		bookOne.setIsbn("1235324523532532ADAC");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = emf.createEntityManager();
		
		//Insert Data into a Database
		/**
		em.getTransaction().begin();
		em.persist(book);
		em.persist(bookOne);
		em.getTransaction().commit();
		**/
		
		//Retrieve book by an Id 
		em.getTransaction().begin();
		Book bk =  em.find(Book.class,11);
		System.out.println(bk);
		em.getTransaction().commit();
		
		
		//Retrieve all books
		em.getTransaction().begin();
		TypedQuery<Book> query = em.createQuery("SELECT book from Book book",Book.class);
		List<Book> books = query.getResultList();
		System.out.println("*******"+books+"*****"+"Books size ----"+books.size()+"*******");
		em.getTransaction().commit();
		/**
		Book bok = new Book();
		bok.setBookId(2);
		bok.setBookTitle("An Introduction to RDBMS");
		bok.setIsbn("RDBMS11242ADSFSFS");
		
		//Update book data 
		em.getTransaction().begin();
		em.merge(bok);
		em.getTransaction().commit();
		*/
		//Delete book from data
		em.getTransaction().begin();
		em.remove(bk);
		em.getTransaction().commit();
	}

}
