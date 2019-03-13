//package br.com.ginezgit.urlshortenerapi.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.enginez.services.citydistances.model.City;
//import br.com.enginez.services.citydistances.service.CityService;
//
//@RestController
//public class CityController {
//
//	private static final String REST_DIRECTORY = "/cities";
//
//	@Autowired
//	private CityService cityService;
//
//	@RequestMapping(value = REST_DIRECTORY, method = RequestMethod.GET)
//	public ResponseEntity<?> getCities() {
//		try {
//			List<City> cities = cityService.getAllCities();
//			return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
//	
//	@RequestMapping(value = REST_DIRECTORY+"/preparedb", method = RequestMethod.POST)
//	public ResponseEntity<?> fillCitiesTable() {
//		try {
//			cityService.createCitiesInDatabase();
//			return new ResponseEntity<>("http://localhost:8080/citydistances/cities",HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//
//	}
//
//}
