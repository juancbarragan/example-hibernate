package com.reece.service;

import com.reece.entities.AddressBook;
import com.reece.entities.Contact;
import com.reece.exception.ContactNotFoundException;
import com.reece.repositories.AddressBookRepository;
import com.reece.repositories.ContactRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Reece Service which exposes the following operations:
 * 
 * <ul>
 * <li>Add Contact to Address Book</li>
 * <li>Create Address Book</li>
 * <li>Get All Contacts from Address Book</li>
 * <li>Remove Contact from Address Book</li>
 * </ul>
 * 
 * @author juancarlosbarraganquintero
 */
@Service
@Transactional
public class ReeceService extends AbstractService{
    
    @Autowired
    AddressBookRepository addressBookRepository;
    @Autowired
    ContactRepository contactRepository;
    
    /**
     * Creates a new Address Book
     * @param name Address Book name
     * @return newly created Address Book
     */
    public AddressBook createAddressBook(String name){
        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        validate(addressBook);
        addressBook = addressBookRepository.save(addressBook);
        return addressBook;
    }
    
    /**
     * Removes a contact from an Address Book
     * @param contactName Contact name to be deleted
     * @param addressBookName Address Book name
     * @throws Exception 
     */
    public void removeContactFromAddressBook(String contactName, String addressBookName) throws ContactNotFoundException{
        // Find the Contact
        Contact contact = contactRepository.findContactByName(contactName, addressBookName);
        
        if(contact == null){
            throw new ContactNotFoundException("Contact with name " + contactName + 
                    " does not exist in the Address Book " + addressBookName);
        }
        
        AddressBook addressBook = contact.getAddressBook();
        
        contactRepository.delete(contact);
        
        addressBook.getContacts().remove(contact);
        
        addressBookRepository.save(addressBook);
        
    }
    
    /**
     * Add a Contact to an Address Book
     * @param addressBookName Address Book name where the contact is to be put
     * @param contactName Contact name
     * @param contactPhone Contact Phone Number
     */
    public void addContactToAddressBook(String addressBookName, String contactName, String contactPhone){
        // Find Address Book by name
        AddressBook addressBook = addressBookRepository.findByName(addressBookName);
        
        Contact contact = new Contact();
        contact.setName(contactName);
        contact.setPhoneNumber(contactPhone);
        contact.setAddressBook(addressBook);
        validate(contact);
        contactRepository.save(contact);
        
        addressBook.getContacts().add(contact);
        addressBookRepository.save(addressBook);
    }
    
    /**
     * Get all Contacts belonging to an Address Book
     * @param addressBookName Address Book name
     * @return a Set of all the Contacts that belong to the Address Book
     */
    public Set<Contact> getAllContactsAddressBook(String addressBookName){
        // Find Address Book by name
        AddressBook addressBook = addressBookRepository.findByName(addressBookName);
        
        return addressBook.getContacts();
    }
    
    /**
     * Get all Contacts across all Address Books
     * 
     * @return a Set containing all the Contacts from all Address Books.
     */
    public Set<Contact> getContactsAllAddressBooks(){
        List<Contact> allContacts;
        allContacts = contactRepository.findAll();
        
        Set<Contact> result = new HashSet<>();
        
        result.addAll(allContacts);
        return result;
    }
}
