package com.videogame.librecritic.service;

import com.videogame.librecritic.dto.UserRegistrationDto;
import com.videogame.librecritic.model.Role;
import com.videogame.librecritic.model.User;
import com.videogame.librecritic.repository.RoleRepository;
import com.videogame.librecritic.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
        user.setRoles(Collections.singletonList(role));
        return userRepository.save(user);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateBio(String username, String bio) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setBio(bio);
            userRepository.save(user);
        }
    }

    public void followUser(String followerUsername, String followingUsername) {
        User follower = userRepository.findByUsername(followerUsername);
        User following = userRepository.findByUsername(followingUsername);
        if (follower != null && following != null && !follower.getUsername().equals(following.getUsername())) {
            if (!follower.getFollowing().contains(following)) {
                follower.getFollowing().add(following);
                userRepository.save(follower);
            }
        }
    }

    public void unfollowUser(String followerUsername, String followingUsername) {
        User follower = userRepository.findByUsername(followerUsername);
        User following = userRepository.findByUsername(followingUsername);
        if (follower != null && following != null) {
            follower.getFollowing().remove(following);
            userRepository.save(follower);
        }
    }

    public java.util.List<User> searchUsers(String query) {
        return userRepository.findByUsernameContainingIgnoreCase(query);
    }
}
