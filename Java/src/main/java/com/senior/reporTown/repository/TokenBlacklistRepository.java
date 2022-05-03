package com.senior.reporTown.repository;

import com.senior.reporTown.model.InvalidToken;
import com.senior.reporTown.model.Report;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TokenBlacklistRepository extends MongoRepository<InvalidToken, String> {
    Optional<InvalidToken> findByJwt(String jwt);
}
