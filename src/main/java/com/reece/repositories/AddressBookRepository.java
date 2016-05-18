package com.reece.repositories;

import com.reece.entities.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * AddressBook repository for JPA Persistence
 * 
 * @author juancarlosbarraganquintero
 */
@org.springframework.stereotype.Repository
@Transactional
public interface AddressBookRepository extends JpaRepository<AddressBook, Long>{
    public AddressBook findByName(String name);
}
