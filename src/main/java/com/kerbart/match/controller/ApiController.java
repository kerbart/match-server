package com.kerbart.match.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kerbart.match.controller.dto.PatientSearchDTO;
import com.kerbart.match.controller.responses.MatchResponse;
import com.kerbart.match.model.FirstName;
import com.kerbart.match.services.PrenomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/")
@Api(value = "Match API")
public class ApiController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

	@Inject
	PrenomService prenomService;
	
	@ApiOperation(value = "Generate random firstname")
	@RequestMapping(value = "/firstname/random", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<MatchResponse> randomFirstname() {
		MatchResponse response = new MatchResponse();
		response.setFirstName(prenomService.random());
		return new ResponseEntity<MatchResponse>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "toto")
	@RequestMapping(value = "/firstname/toto", produces = "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> toto(@ModelAttribute("totoInput") PatientSearchDTO search) {
		
		return new ResponseEntity<String>("test", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Generate random firstname")
	@RequestMapping(value = "/firstname/randoms", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<MatchResponse> randomsFirstname() {
		MatchResponse response = new MatchResponse();
		response.setFirstNames(prenomService.randoms());
		return new ResponseEntity<MatchResponse>(response, HttpStatus.OK);
	}
	
	
	
	
	
	@ApiOperation(value = "Generate random firstname for boy")
	@RequestMapping(value = "/firstname/random/boy", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<MatchResponse> randomFirstnameBoy() {
		MatchResponse response = new MatchResponse();
		response.setFirstName(prenomService.randomBoy());
		return new ResponseEntity<MatchResponse>(response, HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "Generate random firstname for girl")
	@RequestMapping(value = "/firstname/random/girl", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<MatchResponse> randomFirstnameGirl() {
		MatchResponse response = new MatchResponse();
		response.setFirstName(prenomService.randomGirl());
		return new ResponseEntity<MatchResponse>(response, HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "Feed From CSV")
	@RequestMapping(value = "/feed", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<Long> feed() {
		MatchResponse response = new MatchResponse();
		Long numberInserted = 0L;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("Prenoms-UTF8.csv").getFile());
			
			List<String> lines= FileUtils.readLines(file);
			
			for (String line : lines) {
				String[] attributes = line.split(";");
				FirstName prenom = new FirstName();
				// m;croatian;0
				try {
					prenom.setPrenom(attributes[0]);
					prenom.setGenre(attributes[1]);
					prenom.setLangage(attributes[2]);
					prenom.setFrequence(new Double(attributes[3]));
					prenom = prenomService.create(prenom);
					System.out.println(prenom);
					numberInserted++;
				} catch (NumberFormatException e) {
					
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Long>(numberInserted, HttpStatus.OK);
	}
	

}
