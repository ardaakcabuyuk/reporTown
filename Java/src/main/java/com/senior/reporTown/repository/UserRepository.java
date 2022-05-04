package com.senior.reporTown.repository;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.security.UserRole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends MongoRepository<ApplicationUser, String> {
    Optional<ApplicationUser> findByUsername(String username);
    Optional<ApplicationUser> findById(ObjectId id);
    Optional<ApplicationUser> deleteById(ObjectId id);
    List<ApplicationUser> findAllByRole(UserRole role);
}
