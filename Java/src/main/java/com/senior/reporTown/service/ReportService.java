package com.senior.reporTown.service;

import com.senior.reporTown.model.*;
import com.senior.reporTown.repository.ReportRepository;
import com.senior.reporTown.repository.UserRepository;
import com.senior.reporTown.request.ReportRequest;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

import java.util.List;
import java.util.Collections;

@Service
public class ReportService {

    private final NotificationService notificationService;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final GoogleStorageClientService googleStorageClientService;
    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    public ReportService(NotificationService notificationService,
                         ReportRepository reportRepository,
                         UserRepository userRepository,
                         GoogleStorageClientService googleStorageClientService) {
        this.notificationService = notificationService;
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.googleStorageClientService = googleStorageClientService;
    }

    public Report postReport(@AuthenticationPrincipal ApplicationUser authenticatedUser, ReportRequest request) {
        Institution institution = (Institution) userRepository.findById(request.getInstitutionId()).get();
        Report newReport = new Report(
                request.getDescription(),
                request.getCategory(),
                authenticatedUser.getId(),
                request.getInstitutionId(),
                request.getLongitude(),
                request.getLatitude(),
                request.getCity(),
                authenticatedUser.getUsername(),
                ((Citizen)authenticatedUser).getFirstName(),
                ((Citizen)authenticatedUser).getLastName(),
                ((Citizen)authenticatedUser).getProfilePicture(),
                institution.getInstitutionName(),
                null
        );
        reportRepository.save(newReport);
        rewardOwner(newReport, 10);
        logger.info(String.format("A report has been posted by user %s", authenticatedUser.getUsername()));
        return newReport;
    }

    public Report editReport(ObjectId id, String description, String category) {
        Report report = reportRepository.findById(id);
        report.setDescription(description);
        report.setCategory(category);
        reportRepository.save(report);
        return report;
    }

    public int upvoteReport(ObjectId userId, ObjectId reportId) {
        Report report = reportRepository.findById(reportId);
        if (report != null) {
            // if the user already upvoted the post, remove upvote
            if (report.getUpvotes().contains(userId)) {
                report.getUpvotes().remove(userId);
            }

            // if the user has not upvoted the post, upvote
            else {
                report.getUpvotes().add(userId);
                notificationService.notify(userRepository.findById(userId).get().getUsername(), report.getUserId(), reportId, NotificationType.UPVOTE);
            }
            reportRepository.save(report);
            rewardOwner(report, 1);
            return report.getUpvotes().size();
        }
        else {
            return -1;
        }
    }

    public Comment commentToReport(ObjectId userId, ObjectId reportId, String text, String firstName, String lastName,String username) {
        Report report = reportRepository.findById(reportId);
        if (report != null) {
            Comment comment = new Comment(userId, text, firstName, lastName, username);
            report.getComments().add(comment);
            reportRepository.save(report);
            notificationService.notify(username, report.getUserId(), reportId, NotificationType.COMMENT);
            rewardOwner(report, 0.5);
            return comment;
        }
        else {
            return null;
        }
    }

    public Report deleteComment(ObjectId reportId, ObjectId commentId) {
        Report report = reportRepository.findById(reportId);
        if (report != null) {
            report.getComments().removeIf(comment -> comment.getId().toString().equals(commentId.toString()));
            reportRepository.save(report);
            return report;
        }
        else {
            return null;
        }
    }

    public Report getReport(ObjectId reportId) {
        Report report = reportRepository.findById(reportId);
        return report;
    }

    public List<Report> getAllReports(){
        List<Report> reports = reportRepository.findAll();
        Collections.reverse(reports);
        return reports;
    }

    //bu function yanlış
    public List<Report> getAllSolvedReports(){
        List<Report> reports = reportRepository.findAll();
        List<Report> solvedReports = new ArrayList<Report>();
        for(int i = 0; i < reports.size(); i++){
            if(reports.get(i).getSolution() != null){
                solvedReports.add(reports.get(i));
            }
        }
        Collections.reverse(solvedReports);
        return solvedReports;
    }

    public List<Report> getAllSolvedReportsOfficial(ObjectId officialId) {

        Official official = (Official) userRepository.findById(officialId).get();
        List<Report> reports = reportRepository.findAll();
        List<Report> solved = new ArrayList<>();
        for(int i = 0; i < reports.size() ; i++){
            if( (reports.get(i).getOfficial() != null) && (reports.get(i).getOfficial().getId().equals(officialId)) ){
                if(reports.get(i).isResolvedByCitizen() == true){
                    solved.add(reports.get(i));
                }
            }
        }
        return solved;
    }

    public List<Report> officialFeedNotResolvedByCitizen(ObjectId officialId) {

        List<Report> reports = reportRepository.findAll();
        List<Report> reportsNotSolvedByCitizen = new ArrayList<>();
        for(int i = 0; i < reports.size() ; i++){
            if( (reports.get(i).getOfficial() != null) && (reports.get(i).getOfficial().getId().equals(officialId)) ){
                if(reports.get(i).isResolvedByCitizen() == false && reports.get(i).isResolvedByInstitution()==true){
                    reportsNotSolvedByCitizen.add(reports.get(i));
                }
            }
        }
        return reportsNotSolvedByCitizen;
    }

    public List<Report> getAllUnsolvedReportsOfficial(ObjectId officialId) {

        Official official = (Official) userRepository.findById(officialId).get();
        List<Report> reports = reportRepository.findAll();
        List<Report> unsolved = new ArrayList<>();
        for(int i = 0; i < reports.size(); i++){
            if(reports.get(i).getOfficial() != null){
                if(reports.get(i).getOfficial().getId().equals(officialId)){
                    if(reports.get(i).isResolvedByCitizen() == false){
                        unsolved.add(reports.get(i));
                    }
                }
            }
        }
        return unsolved;

    }

    public List<Report> officialFeedReportsWithNoSolution(ObjectId officialId) {
        Official official = (Official) userRepository.findById(officialId).get();
        List<Report> reports = reportRepository.findAll();
        List<Report> reportsNoSolution = new ArrayList<>();
        for(int i = 0; i < reports.size(); i++){
            if(reports.get(i).getOfficial() != null){
                if(reports.get(i).getOfficial().getId().equals(officialId)){
                    if(reports.get(i).isResolvedByCitizen() == false && reports.get(i).isResolvedByInstitution() == false ){
                        reportsNoSolution.add(reports.get(i));
                    }
                }
            }
        }
        return reportsNoSolution;
    }



    public List<Report> getReportsByUser(ObjectId userId) { return reportRepository.findByUserId(userId); }

    public List<Report> getReportsByInstitution(ObjectId userId) {
        return reportRepository.findByInstitutionId(userId); }

    public List<Report> getReportsByOfficial(ObjectId userId) {
        return reportRepository.findByUserId(userId);
    }

    public void approveSolution(ObjectId userId, ObjectId reportId) {
        Report report = reportRepository.findById(reportId);
        Citizen user = (Citizen) userRepository.findById(userId).get();
        report.setResolvedByCitizen(true);
        rewardActionTaker(user, 10);
        rewardActionTaker(userRepository.findById(report.getInstitutionId()).get(), 20);
        report.getSolution().setResolved(true);
        reportRepository.save(report);
        notificationService.notify(user.getUsername(), report.getOfficial().getId(), reportId, NotificationType.SOLUTION_APPROVED);
    }

    public void markAsSolved(ObjectId userId, ObjectId reportId) {
        Report report = reportRepository.findById(reportId);
        Citizen user = (Citizen) userRepository.findById(userId).get();
        report.setResolvedByCitizen(true);
        rewardActionTaker(user, 10);
        rewardActionTaker(userRepository.findById(report.getInstitutionId()).get(), 20);
        reportRepository.save(report);
        if (report.getOfficial() != null) {
            notificationService.notify(user.getUsername(), report.getOfficial().getId(), reportId, NotificationType.MARK_SOLVED);
        }
    }

    public Solution postSolution(ObjectId userId, ObjectId reportId, String description){
        Report report = reportRepository.findById(reportId);
        Official official = (Official) userRepository.findById(userId).get();
        report.setResolvedByInstitution(true);
        rewardActionTaker(official, 10);
        Solution solution = new Solution(description,false);
        report.setSolution(solution);
        reportRepository.save(report);
        notificationService.notify(official.getUsername(), report.getUserId(), reportId, NotificationType.SOLUTION_POSTED);
        return solution;
    }


    public Report rejectSolution(ObjectId userId, ObjectId reportId) {
        Report report = reportRepository.findById(reportId);
        Citizen citizen = (Citizen) userRepository.findById(userId).get();
        if (report.isResolvedByInstitution()) {
            report.setSolution(null);
            report.setResolvedByInstitution(false);
            notificationService.notify(citizen.getUsername(), report.getOfficial().getId(), reportId, NotificationType.SOLUTION_REJECTED);
        }
        reportRepository.save(report);
        return report;
    }

    public void rewardActionTaker(ApplicationUser user, double val) {
        user.setScore(user.getScore() + val);
        userRepository.save(user);
    }

    public void rewardOwner(Report report, double val) {
        ApplicationUser owner = userRepository.findById(report.getUserId()).get();
        owner.setScore(owner.getScore() + val);
        userRepository.save(owner);
    }

    public Report assignOfficialToReport(ObjectId officialId, ObjectId reportId) {
        Report report = reportRepository.findById(reportId);
        Official official = (Official) userRepository.findById(officialId).get();
        report.setOfficial(official);
        reportRepository.save(report);
        notificationService.notify(userRepository.findById(report.getInstitutionId()).get().getUsername(), officialId, reportId, NotificationType.ASSIGNED);
        return report;
    }

    public List<Report> getTrendingReports() {
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(10);
        List<Report> recentReports = reportRepository.findRecent(oneDayAgo);
        Collections.sort(recentReports, Comparator.comparingInt(r -> (r.getUpvotes().size() * 3 + r.getComments().size() * 2)));
        return recentReports;
    }
}
