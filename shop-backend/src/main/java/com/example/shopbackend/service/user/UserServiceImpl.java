package com.example.shopbackend.service.user;

import com.example.shopbackend.exception.DataNotFoundException;
import com.example.shopbackend.exception.InvalidDataException;
import com.example.shopbackend.model.User;
import com.example.shopbackend.repository.UserRepository;
import com.example.shopbackend.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        try {
            if (user.getCreatedAt() == null) {
                user.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            }

            log.info(user.getId().toString());

            return userRepository.save(user);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid user!");
        }
    }

    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataNotFoundException("Data not found!");
        }
    }

    public void deleteAll() {
        try {
            userRepository.deleteAll();
        } catch (Exception e) {
            throw new DataNotFoundException("Data not found!");
        }
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Could not find user")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        log.info(username);

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Could not find user")
        );

        log.info(user.getUsername());
        
        return new MyUserDetails(user, user.getRole().getGrantedAuthorities());
    }
}
