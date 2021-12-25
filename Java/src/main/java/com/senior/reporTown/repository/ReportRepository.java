package com.senior.reporTown.repository;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Report;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReportRepository extends MongoRepository<Report, String>{

    Optional<Report> findBy(ObjectId id);

}
