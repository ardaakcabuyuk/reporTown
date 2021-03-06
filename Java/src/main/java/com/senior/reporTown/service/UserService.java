package com.senior.reporTown.service;

import com.senior.reporTown.model.*;
import com.senior.reporTown.repository.UserRepository;
import com.senior.reporTown.request.RegistrationRequest;
import com.senior.reporTown.security.UserRole;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public ApplicationUser signUpUser(ApplicationUser applicationUser) {
        logger.info(String.format("Signup Request By: %s", applicationUser.getUsername()));
        boolean userExists = userRepository
                .findByUsername(applicationUser.getUsername())
                .isPresent();

        if (userExists) {
            logger.error(String.format("Username %s already taken", applicationUser.getUsername()));
            throw new IllegalStateException("username already taken");
        }

        String encodedPassword = passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);

        userRepository.save(applicationUser);
        logger.info(String.format("User %s is registered successfully", applicationUser.getUsername()));
        return applicationUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public UserDetails findUserById(ObjectId id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));
    }

    public void changeVolunteer(ObjectId userId) {
        Citizen citizen = (Citizen) findUserById(userId);
        if(citizen.isVolunteer()==true){
            citizen.setVolunteer(false);
        }
        else{
            citizen.setVolunteer(true);
        }
        userRepository.save(citizen);
    }

    public void updateBio(ObjectId userId, String bio) {

        ApplicationUser user = (ApplicationUser) findUserById(userId);

        if((user.getRole().toString()).equals("CITIZEN")){
            Citizen citizen = (Citizen) user;
            citizen.setBio(bio);
            userRepository.save(citizen);
        }
        else if((user.getRole().toString()).equals("OFFICIAL")){
            Official official = (Official) user;
            official.setBio(bio);
            userRepository.save(official);
        }
        else if((user.getRole().toString()).equals("INSTITUTION")){
            Institution institution = (Institution) user;
            institution.setBio(bio);
            userRepository.save(institution);
        }
    }

    public void updatePassword(ObjectId userId, String password) {
        ApplicationUser user = (ApplicationUser) findUserById(userId);


        if((user.getRole().toString()).equals("CITIZEN")){
            Citizen citizen = (Citizen) user;
            String encodedPassword = passwordEncoder.encode(password);
            citizen.setPassword(encodedPassword);
            userRepository.save(citizen);
        }
        else if((user.getRole().toString()).equals("OFFICIAL")){
            Official official = (Official) user;
            String encodedPassword = passwordEncoder.encode(password);
            official.setPassword(encodedPassword);
            userRepository.save(official);
        }
        else if((user.getRole().toString()).equals("INSTITUTION")){
            Institution institution = (Institution) user;
            String encodedPassword = passwordEncoder.encode(password);
            institution.setPassword(encodedPassword);
            userRepository.save(institution);
        }
    }

    public void addOfficialHelper(Institution institution, Official official) {//ObjectId officialId){
        institution.getEmployeeIds().add(official.getId().toString());
        userRepository.save(institution);
        return;
    }
    public Official addOfficial(Institution institution, RegistrationRequest request) {
        Official official = new Official(request.getFirstName(),request.getLastName(),
                                request.getEmail(),request.getUsername(), request.getPassword(),
                UserRole.OFFICIAL,request.getPosition(),true,true,
                true,true,institution.getInstitutionName());
        institution.getEmployees().add(official);
        userRepository.save(institution);
        return official;
    }

    public Institution deleteOfficial(ObjectId userId, ObjectId institutionId) {
        Official official = (Official) userRepository.findById(userId).get();

        if (official != null) {

            Institution institution = (Institution) userRepository.findById(institutionId).get();
           institution.getEmployees().removeIf(o -> o.getUsername().equals(official.getUsername()));
           userRepository.deleteById(userId);
           userRepository.save(institution);

           return institution;
        }
        else {
            return null;
        }


    }

    public Institution addPositionToOfficial(Institution institution, String position) {
        institution.getPositions().add(position);
        userRepository.save(institution);
        return institution;
    }

    public List<Institution> getAllInstitutions() {
        return (List<Institution>)(List<?>) userRepository.findAllByRole(UserRole.INSTITUTION);
    }

    public List<ApplicationUser> getAllUsers() {
        List<ApplicationUser> users = userRepository.findAll();
        users = users.stream().map(u -> {
            switch (u.getRole()) {
                case CITIZEN:
                    return (Citizen) u;
                case INSTITUTION:
                    return (Institution) u;
                case OFFICIAL:
                    return (Official) u;
            }
            return u;
        }).collect(Collectors.toList());
        return users;
    }

    public boolean checkUserExists(String identity) {
        return userRepository.findByUsername(identity).isPresent() || userRepository.findByEmail(identity).isPresent();
    }
}
