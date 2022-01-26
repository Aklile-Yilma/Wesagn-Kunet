package com.gov.wesagnkunet;

import java.sql.Date;
import java.util.Arrays;

import java.util.List;

import com.gov.wesagnkunet.admin.data.models.Admin;
import com.gov.wesagnkunet.admin.data.repositories.AdminRepository;
import com.gov.wesagnkunet.client.data.models.Client;
import com.gov.wesagnkunet.client.data.models.Name;
import com.gov.wesagnkunet.client.data.models.Address.Country;
import com.gov.wesagnkunet.client.data.models.Address.Nationality;
import com.gov.wesagnkunet.client.data.models.Client.BloodType;
import com.gov.wesagnkunet.client.data.models.Client.Sex;
import com.gov.wesagnkunet.client.data.repositories.ClientRepository;
import com.gov.wesagnkunet.client.data.repositories.CountryRepository;
import com.gov.wesagnkunet.client.data.repositories.NationalityRepository;
import com.gov.wesagnkunet.lib.auth.UserManager;
import com.gov.wesagnkunet.lib.auth.data.models.User;
import com.gov.wesagnkunet.lib.auth.data.models.User.Role;
import com.gov.wesagnkunet.lib.webcontent.data.models.Tab;
import com.gov.wesagnkunet.lib.webcontent.data.repositories.TabRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class WesagnkunetApplication {

	public static void main(String[] args) {
		SpringApplication.run(WesagnkunetApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AdminRepository adminRepository, UserManager userManager,
			NationalityRepository nationalityRepository, CountryRepository countryRepository,
			ClientRepository clientRepository, TabRepository tabRepository
			) {
		
		
		return new CommandLineRunner(){

			private void createAdmin(){
				
				String USERNAME = "admin@gmail.com";
				String PASSWORD = "password";

				if(adminRepository.findAll().iterator().hasNext()){
					return;
				}
				User user = userManager.createUser(USERNAME, PASSWORD, Role.ADMIN);
				Client client = new Client(
					user,
					new Name("Jermaine", "Lamar", "Cole"),
					null,
					new Date(System.currentTimeMillis()),
					BloodType.A_PLUS,
					Sex.MALE,
					null,
					null
				);
				clientRepository.save(client);
				adminRepository.save(new Admin(client));


				log.info(
					String.format("Created Admin User with username=%s, password=%s", USERNAME, PASSWORD)
				);
			}

			private List<String> getCountries(){
				return Arrays.asList(
						"Afghanistan",
						"Albania",
						"Algeria",
						"Andorra",
						"Angola",
						"Antigua and Barbuda",
						"Argentina",
						"Armenia",
						"Australia",
						"Austria",
						"Azerbaijan",
						"Bahamas",
						"Bahrain",
						"Bangladesh",
						"Barbados",
						"Belarus",
						"Belgium",
						"Belize",
						"Benin",
						"Bhutan",
						"Bolivia",
						"Bosnia and Herzegovina",
						"Botswana",
						"Brazil",
						"Brunei ",
						"Bulgaria",
						"Burkina Faso",
						"Burundi",
						"Côte d'Ivoire",
						"Cabo Verde",
						"Cambodia",
						"Cameroon",
						"Canada",
						"Central African Republic",
						"Chad",
						"Chile",
						"China",
						"Colombia",
						"Comoros",
						"Congo (Congo-Brazzaville)",
						"Costa Rica",
						"Croatia",
						"Cuba",
						"Cyprus",
						"Czechia (Czech Republic)",
						"Democratic Republic of the Congo",
						"Denmark",
						"Djibouti",
						"Dominica",
						"Dominican Republic",
						"Ecuador",
						"Egypt",
						"El Salvador",
						"Equatorial Guinea",
						"Eritrea",
						"Estonia",
						"Eswatini (fmr. \"Swaziland\")",
						"Ethiopia",
						"Fiji",
						"Finland",
						"France",
						"Gabon",
						"Gambia",
						"Georgia",
						"Germany",
						"Ghana",
						"Greece",
						"Grenada",
						"Guatemala",
						"Guinea",
						"Guinea-Bissau",
						"Guyana",
						"Haiti",
						"Holy See",
						"Honduras",
						"Hungary",
						"Iceland",
						"India",
						"Indonesia",
						"Iran",
						"Iraq",
						"Ireland",
						"Israel",
						"Italy",
						"Jamaica",
						"Japan",
						"Jordan",
						"Kazakhstan",
						"Kenya",
						"Kiribati",
						"Kuwait",
						"Kyrgyzstan",
						"Laos",
						"Latvia",
						"Lebanon",
						"Lesotho",
						"Liberia",
						"Libya",
						"Liechtenstein",
						"Lithuania",
						"Luxembourg",
						"Madagascar",
						"Malawi",
						"Malaysia",
						"Maldives",
						"Mali",
						"Malta",
						"Marshall Islands",
						"Mauritania",
						"Mauritius",
						"Mexico",
						"Micronesia",
						"Moldova",
						"Monaco",
						"Mongolia",
						"Montenegro",
						"Morocco",
						"Mozambique",
						"Myanmar (formerly Burma)",
						"Namibia",
						"Nauru",
						"Nepal",
						"Netherlands",
						"New Zealand",
						"Nicaragua",
						"Niger",
						"Nigeria",
						"North Korea",
						"North Macedonia",
						"Norway",
						"Oman",
						"Pakistan",
						"Palau",
						"Palestine State",
						"Panama",
						"Papua New Guinea",
						"Paraguay",
						"Peru",
						"Philippines",
						"Poland",
						"Portugal",
						"Qatar",
						"Romania",
						"Russia",
						"Rwanda",
						"Saint Kitts and Nevis",
						"Saint Lucia",
						"Saint Vincent and the Grenadines",
						"Samoa",
						"San Marino",
						"Sao Tome and Principe",
						"Saudi Arabia",
						"Senegal",
						"Serbia",
						"Seychelles",
						"Sierra Leone",
						"Singapore",
						"Slovakia",
						"Slovenia",
						"Solomon Islands",
						"Somalia",
						"South Africa",
						"South Korea",
						"South Sudan",
						"Spain",
						"Sri Lanka",
						"Sudan",
						"Suriname",
						"Sweden",
						"Switzerland",
						"Syria",
						"Tajikistan",
						"Tanzania",
						"Thailand",
						"Timor-Leste",
						"Togo",
						"Tonga",
						"Trinidad and Tobago",
						"Tunisia",
						"Turkey",
						"Turkmenistan",
						"Tuvalu",
						"Uganda",
						"Ukraine",
						"United Arab Emirates",
						"United Kingdom",
						"United States of America",
						"Uruguay",
						"Uzbekistan",
						"Vanuatu",
						"Venezuela",
						"Vietnam",
						"Yemen",
						"Zambia",
						"Zimbabwe"
				);
			}

			private void insertCountries(){

				if(!countryRepository.findAll().isEmpty())
					return;
				
				log.info(
					String.format("Inserting countries....")
				);

				getCountries().forEach(
					country -> {
						countryRepository.save(new Country(country));
					}
				);

			}

			private List<String> getNationalities(){
				return Arrays.asList(

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
			}

			private void insertNationalities(){
				
				if(nationalityRepository.findAll().iterator().hasNext())
					return;
				
				getNationalities().forEach(
					nationality -> {
						nationalityRepository.save(new Nationality(nationality));
					}
				);
			}

			private void insertTabs(){

				if(tabRepository.findAll().iterator().hasNext())
					return;

				log.info("Inserting Tabs...");
				
				tabRepository.saveAll(
					Arrays.asList(
						new Tab("Home", "/", 0, "client_header", null),
						new Tab("Services", "#", 1, "client_header", null),
						new Tab("About", "/about", 2, "client_header", null),
						new Tab("Contact", "/contact", 3, "client_header", null),

						new Tab("Home", "/", 0, "client_footer", null),
						new Tab("About", "/about", 0, "client_footer", null),
						new Tab("Contact", "/contact", 0, "client_footer", null),
					
						new Tab("Birth Certificates", "/registration/birth/", 0, "client_footer_service", null),
						new Tab("Marriage Certificates", "/registration/marraige", 1, "client_footer_service", null),
						new Tab("Death Certificates", "/registration/death", 2, "client_footer_service", null),

						new Tab("Birth Certificates", "/admin/dashboard/requests/birth", 0, "admin_side", null, "fas fa-child"),
						new Tab("Marriage Certificates", "/admin/dashboard/requests/marriage", 1, "admin_side", null, "fas fa-ring"),
						new Tab("Death Certificates", "/admin/dashboard/requests/death", 2, "admin_side", null, "fas fa-book-dead"),
						new Tab("Contact Messages", "/admin/dashboard/contact", 3, "admin_side", null, "fas fa-envelope")
					)
				);
				
				Tab servicesTab = tabRepository.findByText("Services");
				
				tabRepository.saveAll(
					Arrays.asList(
						new Tab("Birth Certificate", "/registration/birth", 0, "client_header", servicesTab),
						new Tab("Marriage Certificate", "/registration/marriage", 1, "client_header", servicesTab),
						new Tab("Death Certificate", "/registration/death", 2, "client_header", servicesTab)
					)
				);

			}

			private void initialSetup(){
				insertCountries();
				insertNationalities();
				createAdmin();
				insertTabs();
			}

			@Override
			public void run(String... args) throws Exception {
				
				initialSetup();
				
			}
			
			
			
		};
		
		
		

	}

}
