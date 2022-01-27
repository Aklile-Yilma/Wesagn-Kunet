package com.gov.wesagnkunet.client.controllers.services.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.NumberFormat;

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

    @NotBlank(message = "Please provide first name")
    private String firstName;

    @NotBlank(message = "Please provide last name")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @NumberFormat
    private Integer phoneNumber;
    
    @Email(message = "Please provide valid email")
    private String Email;

    private String message;

    
 
    
}
