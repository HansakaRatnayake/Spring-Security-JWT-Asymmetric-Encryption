package com.lezord.app.service.impl;

import com.lezord.app.dto.request.ChangePasswordRequestDTO;
import com.lezord.app.dto.request.ProfileUpdateRequestDTO;
import com.lezord.app.entity.User;
import com.lezord.app.exception.BusinessException;
import com.lezord.app.mappers.UserMapper;
import com.lezord.app.repository.UserRepository;
import com.lezord.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.lezord.app.exception.enums.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username : %s", username)));
    }

    @Override
    public void updateProfileInfo(ProfileUpdateRequestDTO request, String userId) {
        User savedUser = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        this.userMapper.mergeUserInfo(savedUser, request);
        this.userRepository.save(savedUser);
    }

    @Override
    public void changePassword(ChangePasswordRequestDTO request, String userId) {
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) throw new BusinessException(CHANGE_PASSWORD_MISMATCH);

        User savedUser = userRepository.findById(userId).orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        if(!this.passwordEncoder.matches(request.getCurrentPassword(), savedUser.getPassword())) throw new BusinessException(INVALID_CURRENT_PASSWORD);

        savedUser.setPassword(this.passwordEncoder.encode(request.getNewPassword()));
        this.userRepository.save(savedUser);
    }

    @Override
    public void deactivateAccount(String userId) {
        User savedUser = userRepository.findById(userId).orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        if (!savedUser.isEnabled()) throw new BusinessException(ACCOUNT_ALREADY_DEACTIVATED);

        savedUser.setEnabled(false);
        this.userRepository.save(savedUser);
    }

    @Override
    public void reactivateAccount(String userId) {
        User savedUser = userRepository.findById(userId).orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        if (savedUser.isEnabled()) throw new BusinessException(ACCOUNT_ALREADY_ACTIVATED);

        savedUser.setEnabled(true);
        this.userRepository.save(savedUser);

    }

    @Override
    public void deleteAccount(String userId) {

    }


}
