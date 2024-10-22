package viettridao.mockproject.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.exceptions.InvalidParamException;
import viettridao.mockproject.models.PasswordResetToken;
import viettridao.mockproject.models.User;
import viettridao.mockproject.repositories.PasswordResetTokenRepository;
import viettridao.mockproject.repositories.UserRepository;
import viettridao.mockproject.services.imp.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * PasswordResetService
 * Version: 1.0
 * Date: 5/25/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Copy from chatGPT
 */
@Service
@RequiredArgsConstructor
public class PasswordResetService {

    @Value("${reset.token.expiry.minutes}")
    private int expiryMinutes;

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Transactional
    public void createPasswordResetToken(String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("Email not found"));

        PasswordResetToken token = new PasswordResetToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusMinutes(expiryMinutes));

        tokenRepository.save(token);

        String resetUrl = "http://localhost:8088/api/v1/reset-password?token=" + token.getToken();
        emailService.sendMail(user.getEmail(), "Reset Password",
                "<a href=\"" + resetUrl + "\">Click to reset your password</a>");
    }

    @Transactional
    public void resetPassword(String token, String newPassword) throws Exception {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new Exception("Token is invalid or expired");
        }
        if (!userService.validatePassword(newPassword)) {
            throw new InvalidParamException("Password is not strong enough");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(resetToken);
    }
}
