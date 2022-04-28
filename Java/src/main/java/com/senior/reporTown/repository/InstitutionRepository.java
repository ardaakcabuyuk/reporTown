package com.senior.reporTown.repository;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Report;
import org.bson.types.ObjectId;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface InstitutionRepository extends MongoRepository<ApplicationUser, String>{
    Optional<ApplicationUser> findByUsername(String username);
    Optional<ApplicationUser> findById(ObjectId id);
}
