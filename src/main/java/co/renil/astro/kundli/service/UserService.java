package co.renil.astro.kundli.service;

import co.renil.astro.kundli.entity.User;
import co.renil.astro.kundli.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Register a new user
    public User registerUser(User user) {
        // Additional validation or processing logic (e.g., password encoding)
        return userRepository.save(user);
    }

    // Retrieve a user by ID
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    // Retrieve a user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Update an existing user's profile
    public User updateUser(UUID userId, User userDetails) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Update fields as needed
            user.setFullName(userDetails.getFullName());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            // Continue updating other fields...
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found with id " + userId);
    }

    // Delete a user by ID
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

