package ro.siit.airports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.User;
import ro.siit.airports.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*public User login(String username, String password) {
        User user = repo.findByUsernameAndPassword(username, password);
        return user;
    }

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public User findUserByEmail(String email) {
        return repo.findUserByEmail(email);
    }

   /* public boolean userExists(String email) {
        return findUserByEmail(email).isPresent();
    }*/

    public void createUser(User user) {
        /*BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("User");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);*/
    }

    public void createAdmin(User user) {
        /*BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("Admin");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);*/
    }

    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isUserPresent(String email) {
        User u =userRepository.findByEmail(email);
        if(u != null) {
            return true;
        }
        return false;
    }
}

