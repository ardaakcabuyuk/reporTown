package com.senior.reporTown.service;

import com.senior.reporTown.model.Notification;
import com.senior.reporTown.model.NotificationType;
import com.senior.reporTown.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification notify(String actionTaker, ObjectId destUserId, ObjectId reportId,
                               NotificationType type) {
        String description = null;
        if (type == NotificationType.UPVOTE) {
            description = actionTaker + " has upvoted your report.";
        }
        else if (type == NotificationType.COMMENT) {
            description = actionTaker + " has commented to your report.";
        }
        else if (type == NotificationType.ASSIGNED) {
            description = actionTaker + " has assigned you a report.";
        }
        else if (type == NotificationType.SOLUTION_POSTED) {
            description = actionTaker + " has posted a solution to your report.";
        }
        else if (type == NotificationType.SOLUTION_APPROVED) {
            description = actionTaker + " has approved your solution.";
        }
        else if (type == NotificationType.SOLUTION_REJECTED) {
            description = actionTaker + " has rejected your solution.";
        }
        else if (type == NotificationType.TAGGED) {
            description = actionTaker + " has tagged your institution to a report.";
        }
        Notification notification = null;
        if (description != null) {
             notification = new Notification(actionTaker, destUserId, reportId, type, description);
            notificationRepository.save(notification);
        }
        return notification;
    }

    public List<Notification> fetch(ObjectId destUserId) {
        List<Notification> notifications = notificationRepository.findByDestUserId(destUserId);
        return notifications;
    }
}
