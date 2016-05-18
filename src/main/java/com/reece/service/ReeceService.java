package com.reece.service;

import com.reece.entities.AddressBook;
import com.reece.entities.Contact;
import com.reece.repositories.AddressBookRepository;
import com.reece.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReeceService extends AbstractService{
    
    @Autowired
    AddressBookRepository addressBookRepository;
    @Autowired
    ContactRepository contactRepository;
    
    
    public AddressBook createAddressBook(String name){
        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        validate(addressBook);
        addressBook = addressBookRepository.save(addressBook);
        return addressBook;
    }
    
    public void addContactToAddressBook(String addressBookName, String contactName, String contactPhone){
        // Find Address Book by name
        AddressBook addressBook = addressBookRepository.findByName(addressBookName);
        
        Contact contact = new Contact();
        contact.setName(contactName);
        contact.setPhoneNumber(contactPhone);
        contactRepository.save(contact);
        
        addressBook.getContacts().add(contact);
        addressBookRepository.save(addressBook);
    }
}
