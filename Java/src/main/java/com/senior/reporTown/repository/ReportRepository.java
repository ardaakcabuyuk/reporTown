package com.senior.reporTown.repository;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Official;
import com.senior.reporTown.model.Report;
import org.bson.types.ObjectId;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends MongoRepository<Report, String>{
    Report findById(ObjectId id);
    List<Report> findByUserId(ObjectId userId);
    List<Report> findByInstitutionId(ObjectId institutionId);
    List<Report> findByOfficial(Official official);
}
