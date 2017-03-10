package com.kerbart.match.services;

import com.kerbart.match.model.Prenom;
import com.kerbart.match.model.PrenomMetaData;
import com.kerbart.match.model.Like;
import com.kerbart.match.model.User;
import com.kerbart.match.repositories.PrenomMetaDataRepository;
import com.kerbart.match.repositories.PrenomRepository;
import com.kerbart.match.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository("likeService")
@Transactional
public class LikeService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private PrenomRepository prenomRepository;

	@Inject
	private UserRepository userRepo;


	public Like registerLike(String userToken, String prenomToken,  Boolean liked) {
		Like like = new Like();
		User user = userRepo.findByToken(userToken);
		Prenom prenom = prenomRepository.findByToken(prenomToken);
		if (user != null && prenom != null) {
			like.setPrenom(prenom);
			like.setUser(user);
			like.setLiked(liked);
			em.persist(like);
		}
		return like;
	}

}
