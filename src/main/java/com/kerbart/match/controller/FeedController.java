package com.kerbart.match.controller;

import com.kerbart.match.controller.responses.MatchResponse;
import com.kerbart.match.model.Prenom;
import com.kerbart.match.repositories.LikeRepository;
import com.kerbart.match.repositories.PrenomMetaDataRepository;
import com.kerbart.match.repositories.PrenomRepository;
import com.kerbart.match.services.LikeService;
import com.kerbart.match.services.PrenomService;
import com.kerbart.match.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/feed/")
@Api(value = "Match Feed Controller")
public class FeedController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class);

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


	@Value("${database.driver}")
	String databaseDriver;

	@Value("${database.url}")
	String databaseUrl;

	@Value("${database.login}")
	String databaseLogin;

	@Value("${hibernate.dialect}")
	String hibernateDialiect;

	@Value("${hibernate.hbm2ddl.auto}")
	String hibernateHbm2ddlAuto;




	@ApiOperation(value = "Grab external informations on firstnames")
	@RequestMapping(value = "/extranal/info", produces = "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> readExternalDep() throws IOException {
		// france
		Document webPage = null;
		Elements prenoms = null;
		String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

		webPage = Jsoup.connect("http://www.signification-prenom.net/prenom_francais.htm").get();
		prenoms = webPage.select(".style22 a");
		for (Element prenom : prenoms) {
			LOGGER.info("Prenom trouvé (Français) : " + prenom.text());
			prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(),"FRANCE", "FRANCE");
		}

		webPage = Jsoup.connect("http://www.signification-prenom.net/prenom_basque.htm").get();
		prenoms = webPage.select(".style22 a");
		for (Element prenom : prenoms) {
			LOGGER.info("Prenom trouvé (Basque) : " + prenom.text());
			prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(),"FRANCE", "BASQUE");
		}

		webPage = Jsoup.connect("http://www.signification-prenom.net/prenom_breton.htm").get();
		prenoms = webPage.select(".style22 a");
		for (Element prenom : prenoms) {
			LOGGER.info("Prenom trouvé (Breton) : " + prenom.text());
			prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(),"FRANCE", "BRETON");
		}

		webPage = Jsoup.connect("http://www.signification-prenom.net/prenom_catalan.htm").get();
		prenoms = webPage.select(".style22 a");
		for (Element prenom : prenoms) {
			LOGGER.info("Prenom trouvé (Catalan) : " + prenom.text());
			prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(),"FRANCE", "CATALAN");
		}

		webPage = Jsoup.connect("http://www.signification-prenom.net/prenom_provence.htm").get();
		prenoms = webPage.select(".style22 a");
		for (Element prenom : prenoms) {
			LOGGER.info("Prenom trouvé (Provence) : " + prenom.text());
			prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(),"FRANCE", "PROVENCE");
		}

		webPage = Jsoup.connect("http://www.signification-prenom.net/prenom_classique.htm").get();
		prenoms = webPage.select(".style22 a");
		for (Element prenom : prenoms) {
			LOGGER.info("Prenom trouvé (Classique) : " + prenom.text());
			prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(),"FRANCE", "CLASSIQUE");
		}

		webPage = Jsoup.connect("http://www.signification-prenom.net/prenom_rares.htm").get();
		prenoms = webPage.select(".style22 a");
		for (Element prenom : prenoms) {
			LOGGER.info("Prenom trouvé (Classique) : " + prenom.text());
			prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(),"FRANCE", "RARE");
		}


		for (String letter : alphabet) {
			String url = "http://www.signification-prenom.net/prenom-medievaux/prenom_lettre__THE_LETTER_.htm".replaceAll("_THE_LETTER_", letter);
			// find all french firstname and tag them
			Document docPORT = Jsoup.connect(url).get();
			Elements prenomsPORT = docPORT.select(".style22 a");
			for (Element prenom : prenomsPORT) {
				LOGGER.info("Prenom trouvé (medieval) : " + prenom.text());
				prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(),"FRANCE", "MEDIEVAL");
			}
		}

		// arabe
		Document docRB = Jsoup.connect("http://www.signification-prenom.net/prenom_arabe.htm").get();
		Elements prenomsRB = docRB.select(".style22 a");
		for (Element prenom : prenomsRB) {
			LOGGER.info("Prenom trouvé (Arabe) : " + prenom.text());
			prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(), "ARABE", "");
		}

		// berber
		Document docBERBER = Jsoup.connect("http://www.signification-prenom.net/prenom-berbere.htm").get();
		Elements prenomsBERBER = docBERBER.select(".style22 a");
		for (Element prenom : prenomsBERBER) {
			LOGGER.info("Prenom trouvé (berber) : " + prenom.text());
			prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(), "BERBER", "");
		}



		String[] origines = {"portugais", "espagnols", "anglais", "italiens", "allemands", "polonais", "africains", "hebraique"};

		for (String pays : origines) {
			for (String letter : alphabet) {
				String url = "http://www.signification-prenom.net/prenom-__THE_PAYS__/prenom_lettre__THE_LETTER_.htm".replaceAll("_THE_LETTER_", letter).replaceAll("__THE_PAYS__", pays);
				// find all french firstname and tag them
				Document docPORT = Jsoup.connect(url).get();
				Elements prenomsPORT = docPORT.select(".style22 a");
				for (Element prenom : prenomsPORT) {
					LOGGER.info("Prenom trouvé (" + pays+ ") : " + prenom.text());
					prenomService.addPrenomOrigine(StringUtils.stripAccents(prenom.text()).toUpperCase(),  translateOrigine(pays), "");

				}
			}
		}



		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}


	private static String translateOrigine(String origine) {
		switch(origine) {
			case "portugais":
				return "PORTUGAIS";
			case "espagnols":
				return "ESPAGNOL";
			case "anglais":
				return "ANGLAIS";
			case "italiens":
				return "ITALIEN";
			case "allemands":
				return "ALLEMAND";
			case "polonais":
				return "POLONAIS";
			case "africains":
				return "AFRICAIN";
			case "hebraique":
				return "HEBRAIQUE";
			default :
				return origine;
		}

	}



	@ApiOperation(value = "Feed Occurence From CSV")
	@RequestMapping(value = "/feed/occurence", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<Long> feedOccurence() {
		MatchResponse response = new MatchResponse();
		Long numberInserted = 0L;
		HashMap<String, List<HashMap<String, String>>> tree = new HashMap<>();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("nat2015-UTF8.csv").getFile());
			List<String> lines = FileUtils.readLines(file);
			lines.remove(0);

			// building map Prenom/List occurences firt...
			for (String line : lines) {
				String[] elements = line.split(";");

				HashMap<String, String> occurences = new HashMap<>();
				occurences.put("femininMasculin", elements[0]);
				occurences.put("anneeStr", elements[2]);
				occurences.put("nombreStr", elements[3].replaceAll(".0000", ""));

				tree.putIfAbsent(elements[1], new ArrayList<HashMap<String, String>>());
				tree.get(elements[1]).add(occurences);
				numberInserted++;
				LOGGER.info(" Cached " + numberInserted + " elements. map size : " + tree.keySet().size());
			}
			// sorting
			SortedSet<String> keys = new TreeSet<String>(tree.keySet());
			// storing
			for (String key : keys) {
				String prenom = key;
				prenomService.addPrenomAndOccurence(prenom, tree.get(prenom));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<Long>(numberInserted, HttpStatus.OK);
	}


	@ApiOperation(value = "Add metadata for prenoms")
	@RequestMapping(value = "/feed/prenom/metadata", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<Long> addPrenomMetaData() {
		HashMap<String, List<String>> prenomMetaData = new HashMap<>();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("Prenoms-UTF8.csv").getFile());
			List<String> lines= FileUtils.readLines(file);
			lines.remove(0);

			for (String line : lines) {
				String[] attributes = line.split(";");
				String prenomNoAccentUpperCase = StringUtils.stripAccents(attributes[0]).toUpperCase();
				prenomMetaData.putIfAbsent(prenomNoAccentUpperCase, new ArrayList<>());
				String languagesString = attributes[2];
				languagesString = languagesString.replaceAll(", ", ",");
				String[] languages = languagesString.split(",");
				LOGGER.info("For " + prenomNoAccentUpperCase + " : ");
				for (String l : languages) {
					LOGGER.info("	--> Add " + l);
					prenomMetaData.get(prenomNoAccentUpperCase).add(l);
				}
			}
			/*
			for (String prenomKey : prenomMetaData.keySet()) {
				LOGGER.info("Looking for {} in prenom database ...", prenomKey );
				List<Prenom> prenoms = prenomRepository.findByPrenomSansAccentIgnoreCaseContaining(prenomKey);
				for (Prenom p : prenoms) {
					// we found metadata !
					LOGGER.info("Found meta for {}", p.getPrenomSansAccent());
					for (String origin : prenomMetaData.get(prenomKey)) {
						prenomService.addMetaData(p, prenomKey, origin, 0.0);
					}
				}
			}
			*/
			List<Prenom> prenoms = prenomRepository.findAll();
			LOGGER.info("Found " + prenoms.size() + " prenoms (all)");
			// iterate over each prenom.
			for (Prenom prenom : prenoms) {
				LOGGER.info("Analysing " + prenom.getPrenomSansAccent());
				String[] composed = prenom.getPrenomSansAccent().split("-");
				// for each member of first name, try to find exact match
				for (String member : composed) {
					LOGGER.info("Try to find info for " + member);
					List<String> languages = prenomMetaData.get(member);
					if (languages != null) {
						for (String language : languages) {
							prenomService.addMetaData(prenom, member, language, 0.0);
							LOGGER.info("Find metadata for " + prenom.getPrenomSansAccent() + " in member " + member + " : " + language);
						}
					}
				}
			}




		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<Long>(0L, HttpStatus.OK);
	}




}
