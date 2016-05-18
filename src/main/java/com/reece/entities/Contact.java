package com.reece.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entity Bean Class representing a Contact
 * 
 * @author juancarlosbarraganquintero
 */
@Entity
@Table(name="CONTACT")
@NamedQuery(
    name="Contact.findContactByName",
    query="SELECT c from Contact c where c.name = ?1 and c.addressBook.name = ?2"
)
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{contact.name_null}")
    @Size(min = 3, max = 30, message = "{contact.name_size}")
    private String name;

    @NotNull(message = "{contact.phone_null}")
    @Pattern(regexp="[\\d\\-]{10,15}", message = "{contact.phone_pattern}")
    private String phoneNumber;
    
    @ManyToOne
    private AddressBook addressBook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
    
    
}
