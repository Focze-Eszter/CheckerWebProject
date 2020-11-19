package ro.siit.airports.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.User;
import ro.siit.airports.repository.UserRepository;

/*@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = repo.findUserByEmail(email);
    if (user == null) {
        throw new UsernameNotFoundException("User not found");
    }
    return new CustomUserDetail(user);
}
}*/
