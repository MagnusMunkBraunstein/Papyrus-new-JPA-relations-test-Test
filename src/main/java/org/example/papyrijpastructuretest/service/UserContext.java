package org.example.papyrijpastructuretest.service;

import jakarta.servlet.http.HttpSession;
import org.example.papyrijpastructuretest.exception.userContext.UserNotAuthenticatedException;
import org.example.papyrijpastructuretest.exception.userContext.UserNotFoundException;
import org.example.papyrijpastructuretest.model.User;
import org.example.papyrijpastructuretest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserContext {
    private final HttpSession session;
    private final UserRepository userRepository;

    // Constant for session attribute key
    private static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    @Autowired
    public UserContext(HttpSession session, UserRepository userRepository) {
        this.session = session;
        this.userRepository = userRepository;
    }

    // Get current user or throw exception if not found
    public User getCurrentUser() {
        Long userId = (Long) session.getAttribute(CURRENT_USER_ID);

        if (userId == null) {
            throw new UserNotAuthenticatedException("No user logged in");
        }

        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found in repository"));
    }

    // Set current user in session
    public void setCurrentUser(User user) {
        session.setAttribute(CURRENT_USER_ID, user.getId());
    }

    // Clear current user from session
    public void clearCurrentUser() {
        session.removeAttribute(CURRENT_USER_ID);
    }

    // Check if user is logged in
    public boolean hasUser() {
        return session.getAttribute(CURRENT_USER_ID) != null;
    }
}