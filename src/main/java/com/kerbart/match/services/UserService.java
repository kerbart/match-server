package com.kerbart.match.services;

import com.kerbart.match.model.User;
import com.kerbart.match.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository("userService")
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserRepository userRepository;

    public User register(String phoneNumber) {

        List<User> users = userRepository.findByPhoneNumber(phoneNumber);
        if (users != null && users.size() == 1) {
            return users.get(0);
        }
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setToken(UUID.randomUUID().toString());
        em.persist(user);
        return user;
    }

    /**
     * TODO : maybe more elegant and safe way to get a user
     *
     * @param token
     * @return
     */
    public User findUserByToken(String token) {
        User user = userRepository.findByToken(token);
        return user;
    }

}
