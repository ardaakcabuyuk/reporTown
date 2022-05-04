package com.senior.reporTown.repository;

import com.senior.reporTown.model.Notification;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    Notification findById(ObjectId id);
    List<Notification> findByDestUserId(ObjectId id);
}
