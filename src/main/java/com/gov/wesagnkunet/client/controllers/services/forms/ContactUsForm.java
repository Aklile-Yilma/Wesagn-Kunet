package com.gov.wesagnkunet.client.controllers.services.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="client_message")
public class ContactUsForm {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    
    private String firstName;

    private String lastName;

    private Integer phoneNumber;
    
    private String Email;

    
    private String message;

    
 
    
}
