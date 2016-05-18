package com.reece.service;

import com.reece.entities.AddressBook;
import com.reece.entities.Contact;
import com.reece.repositories.AddressBookRepository;
import com.reece.repositories.ContactRepository;
import java.util.Set;
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
    
    public void removeContactFromAddressBook(String contactName, String addressBookName) throws Exception{
        // Find the Contact
        Contact contact = contactRepository.findContactByName(contactName, addressBookName);
        
        if(contact == null){
            throw new Exception("Contact with name " + contactName + 
                    " does not exist in the Address Book " + addressBookName);
        }
        
        AddressBook addressBook = contact.getAddressBook();
        
        contactRepository.delete(contact);
        
        addressBook.getContacts().remove(contact);
        
        addressBookRepository.save(addressBook);
        
    }
    
    public void addContactToAddressBook(String addressBookName, String contactName, String contactPhone){
        // Find Address Book by name
        AddressBook addressBook = addressBookRepository.findByName(addressBookName);
        
        Contact contact = new Contact();
        contact.setName(contactName);
        contact.setPhoneNumber(contactPhone);
        contact.setAddressBook(addressBook);
        contactRepository.save(contact);
        
        addressBook.getContacts().add(contact);
        addressBookRepository.save(addressBook);
    }
    
    public Set<Contact> getAllContactsAddressBook(String addressBookName){
        // Find Address Book by name
        AddressBook addressBook = addressBookRepository.findByName(addressBookName);
        
        return addressBook.getContacts();
    }
}
