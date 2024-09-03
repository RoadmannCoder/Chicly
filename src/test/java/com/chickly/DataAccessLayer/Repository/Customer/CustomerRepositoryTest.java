package com.chickly.DataAccessLayer.Repository.Customer;

import com.chickly.DataAccessLayer.Entities.Account;
import com.chickly.DataAccessLayer.Entities.Address;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.DataAccessLayer.Repository.ProductRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerRepositoryTest {

    private static CustomerRepository customerRepository;
    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("h2Testing");
        customerRepository = new CustomerRepository(entityManagerFactory.createEntityManager());
    }

    @Test
    void testInsertCustomerToDB(){

        Customer customer = new Customer("Mandour","Waleed", BigDecimal.valueOf(300000L), new Date(96, 3, 12),"mandour.waleed@hotmail.com","0123456789","Softwar Engineer",new Address("Haram","Helwan","12111","YES"),new Account("ManWal","123"));
        customerRepository.create(customer);
        assertNotNull(customer.getId());
        assertSame(customerRepository.findBy("id",customer.getId()).getId(),customer.getId());
    }
    @Test
    void testInsertEmptyCustomerToDB(){
        Customer customer = new Customer();
        assertThrows(RuntimeException.class,()-> customerRepository.create(customer));
    }
    @Test
    void testUpdateCustomerToDB(){
        Customer customer = customerRepository.findBy("id",1);
        Customer customer1 = new Customer(customer.getFirstName(),customer.getLastName(),customer.getCreditLimit(),customer.getDateOfBirth(),customer.getEmail(),customer.getPhoneNumber(),customer.getJob(),customer.getAddress(),customer.getAccount());
        customer.setFirstName("Ghandy");
        customerRepository.update(customer);
        assertNotSame(customerRepository.findBy("id",customer.getId()).getFirstName(),customer1.getFirstName());
        assertSame(customerRepository.findBy("id",customer.getId()).getFirstName(),customer.getFirstName());
    }
    @Test
    void testDeleteCustomerFromDB(){
        customerRepository.deleteById(1);
        assertThrows(RuntimeException.class,()->customerRepository.findBy("id",1));

    }



    @AfterAll
    public static void tearDownClass(){
        customerRepository.entityManager.close();
        entityManagerFactory.close();
    }

}
