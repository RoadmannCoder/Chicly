package com.chickly.DataAccessLayer.Repository.Customer;

import com.chickly.DataAccessLayer.Entities.Account;
import com.chickly.DataAccessLayer.Entities.Address;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class CustomerRepositoryTest {

    private static CustomerRepository customerRepository;


    @BeforeAll
    public static void setUp() {
        customerRepository = new CustomerRepository();
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
    void testCheckEmailIfFound(){
        Customer customer = new Customer("Mandour","Waleed",
                BigDecimal.valueOf(300000L), new Date(96, 3, 12),
                "mandour.waleed@hotmail.com","0123456789",
                "Softwar Engineer",new Address("Haram","Helwan","12111","YES"),new Account("ManWal","123"));

        Customer customer2 = new Customer("Waleed","Mandour",
                BigDecimal.valueOf(300000L), new Date(96, 3, 12),
                "mandour.waled@hotmail.com","01113119989",
                "Softwar Engineer",new Address("Haram","Helwan","12111","YES"),new Account("WalMan","123"));
        customerRepository.create(customer);
        customerRepository.create(customer2);
        assertSame(true,customerRepository.checkEmailIfFound("mandour.waleed@hotmail.com"));
        assertSame(false,customerRepository.checkEmailIfFound("waleed@hotmail.com"));
    }
    @Test
    void testCheckEmailAndPasswordAreValid(){
        Customer customer = new Customer("Mandour","Waleed",
                BigDecimal.valueOf(300000L), new Date(96, 3, 12),
                "mandour.waleed@hotmail.com","0123456789",
                "Softwar Engineer",new Address("Haram","Helwan","12111","YES"),new Account("ManWal","123"));
        Customer customer2 = new Customer("Waleed","Mandour",
                BigDecimal.valueOf(300000L), new Date(96, 3, 12),
                "mandour.waled@hotmail.com","01113119989",
                "Softwar Engineer",new Address("Haram","Helwan","12111","YES"),new Account("WalMan","123"));

        customerRepository.create(customer);
        customerRepository.create(customer2);
        assertSame(true,customerRepository.checkUsernameAndPasswordAreValid("mandour.waleed@hotmail.com","123"));
        assertSame(false,customerRepository.checkUsernameAndPasswordAreValid("mandour.waleed@hotmail.com","1234"));
        assertSame(false,customerRepository.checkUsernameAndPasswordAreValid("mandour.waed@hotmail.com","123"));
    }
    @Test
    void testDeleteCustomerFromDB(){
        customerRepository.deleteById(1);
        assertThrows(RuntimeException.class,()->customerRepository.findBy("id",1));

    }


//
//    @AfterAll
//    public static void tearDownClass(){
//        customerRepository.entityManager.close();
//        entityManagerFactory.close();
//    }

}
