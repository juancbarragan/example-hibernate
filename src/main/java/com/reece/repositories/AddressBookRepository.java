package com.reece.repositories;

import com.reece.entities.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
@Transactional
public interface AddressBookRepository extends JpaRepository<AddressBook, Long>{
    
}
