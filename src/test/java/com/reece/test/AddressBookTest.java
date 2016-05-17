package com.reece.test;

import com.reece.entities.AddressBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.reece.repositories.AddressBookRepository;
import com.reece.service.ReeceService;
import org.junit.Assert;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@EnableTransactionManagement
public class AddressBookTest {
    @Autowired
    AddressBookRepository addressBookRepository;
    
    @Autowired
    ReeceService reeceService;

    @Test
    public void createAddressBookTest() {
        AddressBook addressBook = reeceService.createAddressBook("Address Book 1");
        
        addressBookRepository.findOne(addressBook.getId());
        
        Assert.assertNotNull("Address Book 1 was not saved", addressBook);
    }
}
