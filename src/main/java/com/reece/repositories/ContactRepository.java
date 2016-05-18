package com.reece.repositories;

import com.reece.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Contact repository for JPA Persistence
 * 
 * @author juancarlosbarraganquintero
 */
public interface ContactRepository extends JpaRepository<Contact, Long>{
    public Contact findContactByName(String name, String addressBookName);
}
