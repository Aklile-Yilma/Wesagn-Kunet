package com.gov.wesagnkunet;

import java.util.Arrays;
import java.util.List;

import com.gov.wesagnkunet.admin.data.models.Admin;
import com.gov.wesagnkunet.admin.data.repositories.AdminRepository;
import com.gov.wesagnkunet.client.data.models.Name;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;
import com.gov.wesagnkunet.client.data.repositories.NationalityRepository;
import com.gov.wesagnkunet.lib.auth.UserManager;
import com.gov.wesagnkunet.lib.auth.data.models.User;
import com.gov.wesagnkunet.lib.auth.data.models.User.Role;

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

				List<String> nationalities = Arrays.asList(

						"Afghan",
						"Albanian",
						"Algerian",
						"American",
						"Andorran",
						"Angolan",
						"Anguillan",
						"Citizen of Antigua and Barbuda",
						"Argentine",
						"Armenian",
						"Australian",
						"Austrian",
						"Azerbaijani",

						"Bahamian",
						"Bahraini",
						"Bangladeshi",
						"Barbadian",
						"Belarusian",
						"Belgian",
						"Belizean",
						"Beninese",
						"Bermudian",
						"Bhutanese",
						"Bolivian",
						"Citizen of Bosnia and Herzegovina",
						"Botswanan",
						"Brazilian",
						"British",
						"British Virgin Islander",
						"Bruneian",
						"Bulgarian",
						"Burkinan",
						"Burmese",
						"Burundian",

						"Cambodian",
						"Cameroonian",
						"Canadian",
						"Cape Verdean",
						"Cayman Islander",
						"Central African",
						"Chadian",
						"Chilean",
						"Chinese",
						"Colombian",
						"Comoran",
						"Congolese (Congo)",
						"Congolese (DRC)",
						"Cook Islander",
						"Costa Rican",
						"Croatian",
						"Cuban",
						"Cymraes",
						"Cymro",
						"Cypriot",
						"Czech",

						"Danish",
						"Djiboutian",
						"Dominican",
						"Citizen of the Dominican Republic",
						"Dutch",

						"East Timorese",
						"Ecuadorean",
						"Egyptian",
						"Emirati",
						"English",
						"Equatorial Guinean",
						"Eritrean",
						"Estonian",
						"Ethiopian",

						"Faroese",
						"Fijian",
						"Filipino",
						"Finnish",
						"French",

						"Gabonese",
						"Gambian",
						"Georgian",
						"German",
						"Ghanaian",
						"Gibraltarian",
						"Greek",
						"Greenlandic",
						"Grenadian",
						"Guamanian",
						"Guatemalan",
						"Citizen of Guinea-Bissau",
						"Guinean",
						"Guyanese",

						"Haitian",
						"Honduran",
						"Hong Konger",
						"Hungarian",
						"Icelandic",
						"Indian",
						"Indonesian",
						"Iranian",
						"Iraqi",
						"Irish",
						"Israeli",
						"Italian",
						"Ivorian",

						"Jamaican",
						"Japanese",
						"Jordanian",
						"Kazakh",
						"Kenyan",
						"Kittitian",
						"Citizen of Kiribati",
						"Kosovan",
						"Kuwaiti",
						"Kyrgyz",

						"Lao",
						"Latvian",
						"Lebanese",
						"Liberian",
						"Libyan",
						"Liechtenstein citizen",
						"Lithuanian",
						"Luxembourger",
						"Macanese",
						"Macedonian",
						"Malagasy",
						"Malawian",
						"Malaysian",
						"Maldivian",
						"Malian",
						"Maltese",
						"Marshallese",
						"Martiniquais",
						"Mauritanian",
						"Mauritian",
						"Mexican",
						"Micronesian",
						"Moldovan",
						"Monegasque",
						"Mongolian",
						"Montenegrin",
						"Montserratian",
						"Moroccan",
						"Mosotho",
						"Mozambican",

						"Namibian",
						"Nauruan",
						"Nepalese",
						"New Zealander",
						"Nicaraguan",
						"Nigerian",
						"Nigerien",
						"Niuean",
						"North Korean",
						"Northern Irish",
						"Norwegian",

						"Omani",
						"Pakistani",
						"Palauan",
						"Palestinian",
						"Panamanian",
						"Papua New Guinean",
						"Paraguayan",
						"Peruvian",
						"Pitcairn Islander",
						"Polish",
						"Portuguese",
						"Prydeinig",
						"Puerto Rican",
						"Qatari",
						"Romanian",
						"Russian",
						"Rwandan",
						"Salvadorean",
						"Sammarinese",
						"Samoan",
						"Sao Tomean",
						"Saudi Arabian",
						"Scottish",
						"Senegalese",
						"Serbian",
						"Citizen of Seychelles",
						"Sierra Leonean",
						"Singaporean",
						"Slovak",
						"Slovenian",
						"Solomon Islander",
						"Somali",
						"South African",
						"South Korean",
						"South Sudanese",
						"Spanish",
						"Sri Lankan",
						"St Helenian",
						"St Lucian",
						"Stateless",
						"Sudanese",
						"Surinamese",
						"Swazi",
						"Swedish",
						"Swiss",
						"Syrian",

						"Taiwanese",
						"Tajik",
						"Tanzanian",
						"Thai",
						"Togolese",
						"Tongan",
						"Trinidadian",
						"Tristanian",
						"Tunisian",
						"Turkish",
						"Turkmen",
						"Turks and Caicos Islander",
						"Tuvaluan",

						"Ugandan",
						"Ukrainian",
						"Uruguayan",
						"Uzbek",
						"Vatican citizen",
						"Citizen of Vanuatu",
						"Venezuelan",
						"Vietnamese",
						"Vincentian",

						"Wallisian",
						"Welsh",
						"Yemeni",
						"Zambian",
						"Zimbabwean"

				
				);
						



				nationalities.forEach(nationality -> {
					nationalityRepository.save(new Nationality(nationality));
				});
			}
			
			
			
		};
		
		
		

	}

}
