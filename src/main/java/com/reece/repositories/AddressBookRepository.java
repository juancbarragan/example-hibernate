package com.reece.repositories;

import com.reece.entities.AddressBook;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface AddressBookRepository extends CrudRepository<AddressBook, Long>{
    
}
