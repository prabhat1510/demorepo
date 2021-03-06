/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.jpademo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;



public class App {
    public static void main(String[] args) {
        Employee employee = new Employee();
        Customer customer = new Customer();
        employee.setEmpId(2);
        employee.setEmpName("Ramesh");
        customer.setCustomerName("Simran");
        customer.setCustomerSecretCode("abcdef");
        
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
        EntityManager em = emf.createEntityManager();
        //Insert data in database using persist method
        /**
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        **/
        //Retrieve data from database
        em.getTransaction().begin();
        Employee emp =	em.find(Employee.class, 1);
        System.out.println("**********"+emp.getEmpName()+"**********");
        Customer cust = em.find(Customer.class, 3);
        System.out.println("**********"+cust+"**********");
        em.getTransaction().commit();
        
        /**
        //Remove data from database
        em.getTransaction().begin();
        em.remove(cust);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Customer cus = em.find(Customer.class, 2);
        System.out.println("***After removing*******"+cus+"**********");
        em.getTransaction().commit();
        **/
        //Update the entity or record
        em.getTransaction().begin();
        employee.setEmpName("Parag Agrawal");
        em.merge(employee);
        em.getTransaction().commit();
        
        //Retrieve all customer
        em.getTransaction().begin();
        TypedQuery<Customer> q = em.createQuery("SELECT customer from Customer customer WHERE customer.customerName='Rajesh'",Customer.class);
        List<Customer> customers = q.getResultList();
        /**
        Iterator itr = customers.iterator();
        while(itr.hasNext()) {
        	System.out.println("-*****-"+(itr.next()).toString());
        }**/
        for(Customer cus:customers) {
        	System.out.println(cus);
        }
        
       System.out.println("******************************************");
       
        System.out.println("**************"+customers+"**************");
        em.getTransaction().commit();
        
        //Retrieve all employee
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT employee from Employee employee");
        List<Employee> employees = query.getResultList();
        System.out.println("***Emp---*********"+employees+"************");
        em.getTransaction().commit();
        
        //Using native query retrieve all employees
        em.getTransaction().begin();
        Query qry = em.createNativeQuery("select * from employee_master");
        List<Employee> emps = qry.getResultList();
        Employee employ =  new Employee();
       // employ.setEmpId(emps.get(0).getEmpId());
        System.out.println("***Employuess---*********"+emps+"****"+ employ+"********");
        for(int i =0;i<emps.size();i++) {
        	//System.out.println("-----------------"+(emps.get(i)).getEmpId()+"----");	
        	System.out.println("-----------------"+emps.get(i)+"-----------------");
        }
        for(Object empl:emps) {
        	System.out.println(empl);
        }
        em.getTransaction().commit();
        /**
        List<Customer> cs = em.createNativeQuery(
        		"SELECT * FROM customer", Customer.class )
        		.getResultList();**/
        Query qy = em.createNativeQuery("SELECT * FROM customer", Customer.class);
        List<Customer> cs = qy.getResultList();
        		System.out.println(cs);
        
        //Dynamic Queries with Parameters
        // SELECT aliasName from EntityClass aliasName WHERE aliasName.attribute=:parameterName		
       String sqlStr ="SELECT c from Customer c WHERE c.customerName=:custName" ;
        em.getTransaction().begin();
        TypedQuery<Customer> qey = em.createQuery(sqlStr,Customer.class);
        //qey.setParameter("customerName", "Rajesh");
        qey.setParameter("custName", customer.getCustomerName());
                Customer custmr = qey.getSingleResult();
                System.out.println("--Customer----"+custmr);
                
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        //select e from employee_master where e.empName='Parag Agrawal'
        String empSqlStr = "SELECT employee from Employee employee WHERE employee.empName=:eName";
        TypedQuery<Employee> qary = em.createQuery(empSqlStr,Employee.class);
        qary.setParameter("eName", employee.getEmpName());
        Employee emplye= qary.getSingleResult();
        System.out.println("--Employee----"+emplye);
    }
}
