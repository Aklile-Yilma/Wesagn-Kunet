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

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min=8, message = "Your password should be at least 8 characters long.")
	private String password;

	@NotBlank
	private String confirmPassword;

	@NotBlank
	private String firstName;//

	@NotBlank
	private String middleName;//

	@NotBlank
	private String lastName;//

	@NotNull
	private MultipartFile photo;//

	@NotNull
	private Date dateOfBirth;//

	@NotNull
	private BloodType bloodType;//

	@NotNull
	private Sex sex;//

	private AddressForm address;

	@NotBlank
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
