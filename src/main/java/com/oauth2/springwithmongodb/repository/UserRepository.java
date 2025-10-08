package com.oauth2.springwithmongodb.repository;

import com.oauth2.springwithmongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByName(String name); // ✅ changed from findByUserName

    // ✅ Removed findByUserId, because "id" is already handled by MongoRepository
}
