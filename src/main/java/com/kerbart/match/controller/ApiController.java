package com.kerbart.match.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.inject.Inject;

import com.kerbart.match.controller.responses.LikeResponse;
import com.kerbart.match.controller.responses.LikesResponse;
import com.kerbart.match.controller.responses.UserResponse;
import com.kerbart.match.model.*;
import com.kerbart.match.repositories.PrenomMetaDataRepository;
import com.kerbart.match.repositories.LikeRepository;
import com.kerbart.match.repositories.PrenomRepository;
import com.kerbart.match.services.LikeService;
import com.kerbart.match.services.PrenomService;
import com.kerbart.match.services.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.kerbart.match.controller.dto.PatientSearchDTO;
import com.kerbart.match.controller.responses.MatchResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/")
@Api(value = "Match API")
public class ApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

	@Inject
	PrenomMetaDataRepository firstNameRepository;

	@Inject
	PrenomRepository prenomRepository;

	@Inject
	PrenomService prenomService;

	@Inject
	UserService userSerivce;

	@Inject
	LikeService likeService;


	@Inject
	LikeRepository likeRepository;


	@ApiOperation(value = "Register a new user based on its phone number")
	@RequestMapping(value = "/user/{userToken}/likes", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Like>> listLikes(@PathVariable("userToken") String userToken) {
		return new ResponseEntity<>(likeRepository.getLikes(userToken), HttpStatus.OK);
	}


	@ApiOperation(value = "Register a new user based on its phone number")
	@RequestMapping(value = "/register/{phoneNumber}", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<UserResponse> registerUser(@PathVariable("phoneNumber") String phoneNumber) {
		User user = userSerivce.register(phoneNumber);
		UserResponse userResponse = new UserResponse();
		userResponse.setPhoneNumber(user.getPhoneNumber());
		userResponse.setToken(user.getToken());
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}


	@ApiOperation(value = "Like a firstname")
	@RequestMapping(value = "/like/{userToken}/{prenomToken}", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<LikeResponse> like(@PathVariable("userToken") String userToken, @PathVariable("prenomToken") String prenomToken) {
		Like like = likeService.registerLike(userToken, prenomToken, true);
		return new ResponseEntity<>(new LikeResponse(like), HttpStatus.OK);
	}

	@ApiOperation(value = "Dislike a firstname")
	@RequestMapping(value = "/dislike/{userToken}/{prenomToken}", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<LikeResponse> dislike(@PathVariable("userToken") String userToken, @PathVariable("prenomToken") String prenomToken) {
		Like like =  likeService.registerLike(userToken, prenomToken, true);
		return new ResponseEntity<>(new LikeResponse(like), HttpStatus.OK);
	}

	public ResponseEntity<LikesResponse> getAllLikes() {
		return  new ResponseEntity<LikesResponse>(new LikesResponse(), HttpStatus.OK);
	}


	@ApiOperation(value = "Randoms firstNames")
	@RequestMapping(value = "/randoms/{age}/{genre}/{userToken}", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Prenom>> randoms(@PathVariable("age") PrenomAge age,
												@PathVariable("genre") PrenomGenre genre,
												@PathVariable("userToken") String userToken ) {
		return new ResponseEntity<List<Prenom>>(
				prenomService.getRandomsPrenoms(
						genre.equals(PrenomGenre.MASCULIN) || genre.equals(PrenomGenre.LES_DEUX) ,
						genre.equals(PrenomGenre.FEMININ) || genre.equals(PrenomGenre.LES_DEUX) ,
						new ArrayList<String>(), age, userToken),
				HttpStatus.OK);
	}

}
