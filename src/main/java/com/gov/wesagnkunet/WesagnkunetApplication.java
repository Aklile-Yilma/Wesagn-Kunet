package com.gov.wesagnkunet;

import java.util.Arrays;

import java.util.List;

import com.gov.wesagnkunet.admin.data.repositories.AdminRepository;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;
import com.gov.wesagnkunet.client.data.repositories.NationalityRepository;
import com.gov.wesagnkunet.lib.auth.UserManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class WesagnkunetApplication {

	public static void main(String[] args) {
		SpringApplication.run(WesagnkunetApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AdminRepository adminRepository, UserManager userManager,
			NationalityRepository nationalityRepository) {
		
		
		return new CommandLineRunner(){

			@Override
			public void run(String... args) throws Exception {
				//	User user = userManager.createUser("admin@gmail.com", "password", Role.ADMIN);
				//	adminRepository.save(new Admin(user, new Name("Abreham", "Atlaw", "Alemu")));
			}
			
			
			
		};
		
		
		

	}

}
