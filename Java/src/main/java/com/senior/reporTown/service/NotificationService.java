package com.senior.reporTown.service;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.Notification;
import com.senior.reporTown.model.NotificationType;
import com.senior.reporTown.repository.NotificationRepository;
import com.senior.reporTown.repository.UserRepository;
import com.senior.reporTown.security.UserRole;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    NotificationRepository notificationRepository;
    UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public Notification notify(String actionTaker, ObjectId destUserId, ObjectId reportId,
                               NotificationType type) {
        String description = null;
        ApplicationUser destUser = userRepository.findById(destUserId).get();
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
            if (destUser.getRole() == UserRole.CITIZEN)
                description = actionTaker + " has posted a solution to your report.";
            else if (destUser.getRole() == UserRole.INSTITUTION) {
                description = actionTaker + " has posted a solution to a report that your institution is tagged.";
            }
        }
        else if (type == NotificationType.SOLUTION_APPROVED) {
            if (destUser.getRole() == UserRole.OFFICIAL)
                description = actionTaker + " has approved your solution.";
            else if (destUser.getRole() == UserRole.INSTITUTION)
                description = actionTaker + " has approved a solution to a report that your institution is tagged.";
        }
        else if (type == NotificationType.SOLUTION_REJECTED) {
            if (destUser.getRole() == UserRole.OFFICIAL)
                description = actionTaker + " has rejected your solution.";
            else if (destUser.getRole() == UserRole.INSTITUTION)
                description = actionTaker + " has rejected a solution to a report that your institution is tagged.";
        }
        else if (type == NotificationType.MARK_SOLVED) {
            if (destUser.getRole() == UserRole.OFFICIAL)
                description = actionTaker + " has marked a report that you are assigned to as solved.";
            else if (destUser.getRole() == UserRole.INSTITUTION)
                description = actionTaker + " has marked a report that your institution is tagged as solved.";
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

    public Notification notifyReward(int checkpoint, ObjectId subject) {
        String description = "Congratulations! You have reached to " + checkpoint + " points! Keep up the work!";
        Notification notification = new Notification(userRepository.findById(subject).get().getUsername(), subject, null, NotificationType.REWARD, description);
        notificationRepository.save(notification);
        return notification;
    }

    public List<Notification> fetch(ObjectId destUserId) {
        List<Notification> notifications = notificationRepository.findByDestUserId(destUserId);
        return notifications;
    }
}
