package com.gov.wesagnkunet.client.controllers.auth.forms;

import java.sql.Date;

import javax.persistence.Transient;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gov.wesagnkunet.client.controllers.services.forms.components.AddressForm;
import com.gov.wesagnkunet.client.data.models.Address;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.Contact;
import com.gov.wesagnkunet.client.data.models.Name;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.models.Client.BloodType;
import com.gov.wesagnkunet.client.data.models.Client.Sex;
import com.gov.wesagnkunet.client.data.repositories.ClientRepository;
import com.gov.wesagnkunet.lib.auth.UserManager;
import com.gov.wesagnkunet.lib.auth.data.models.User;
import com.gov.wesagnkunet.lib.auth.data.models.User.Role;
import com.gov.wesagnkunet.lib.auth.exceptions.UserExistsException;
import com.gov.wesagnkunet.lib.media.services.FileStorageService;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SignUpForm {

	@Transient
	private UserManager userManager;

	@Transient
	private FileStorageService storageService;

	@Transient
	private ClientRepository clientRepository;


	public SignUpForm(UserManager userManager, FileStorageService storageService, ClientRepository clientRepository){
		this.userManager = userManager;
		this.storageService = storageService;
		this.clientRepository = clientRepository;
	}

	@Email(message = "Please provide a valid email")
	private String email;

	@Size(min=8, message = "Your password should be at least 8 characters long.")
	private String password;

	@NotBlank
	private String confirmPassword;

	@NotBlank(message = "First name is required")
	private String firstName;//

	@NotBlank(message = "Middle name is required")
	private String middleName;//

	@NotBlank(message = "Last name is required")
	private String lastName;//

	@NotNull(message = "Photo is required")
	private MultipartFile photo;//

	@NotNull(message = "Date of birth is required")
	private Date dateOfBirth;//

	@NotNull(message = "Please Select your blood type")
	private BloodType bloodType;//

	@NotNull(message = "Please Select your your type")
	private Sex sex;//

	private AddressForm address;

	@NotBlank(message = "Phone number is required")
	@NumberFormat
	private String phoneNumber;

	@AssertFalse(message = "User with this email already exists")
	public boolean isUserExists(){
		if(email == null)
			return false;
		return userManager.userExists(email);
	}

	@AssertTrue(message = "Passwords do not match")
	public boolean isPasswordsMatch(){
		if(password == null || confirmPassword == null)
			return true;
		return password.equals(confirmPassword);
	}

	@AssertTrue(message = "Invalid Date")
	public boolean isBirthDateValid(){
		if(dateOfBirth == null)
			return true;
		return new Date(System.currentTimeMillis()).after(dateOfBirth);
	}

	public Client createClient() throws UserExistsException{
		User user = userManager.createUser(email, password, Role.CLIENT);
		Client client = new Client(
			user,
			new Name(firstName, middleName, lastName),
			storageService.store(photo),
			dateOfBirth,
			bloodType,
			sex,
			address.toAddress(),
			new Contact(
				email,
				phoneNumber
			)
		);
		clientRepository.save(client);
		return client;

	}

}
