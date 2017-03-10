package com.kerbart.match.repositories;

import com.kerbart.match.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByPhoneNumber(String phoneNumber);

    User findByToken(String token);

}
