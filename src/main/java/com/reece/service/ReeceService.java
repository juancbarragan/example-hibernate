package com.reece.service;

import com.reece.entities.AddressBook;
import com.reece.repositories.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReeceService {
    
    @Autowired
    AddressBookRepository addressBookRepository;
    
    
    public AddressBook createAddressBook(String name){
        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        addressBook = addressBookRepository.save(addressBook);
        return addressBook;
    }
}
