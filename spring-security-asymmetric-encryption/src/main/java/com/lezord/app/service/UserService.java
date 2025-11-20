package com.lezord.app.service;

import com.lezord.app.dto.request.ChangePasswordRequestDTO;
import com.lezord.app.dto.request.ProfileUpdateRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void updateProfileInfo(ProfileUpdateRequestDTO request, String userId);

    void changePassword(ChangePasswordRequestDTO request, String userId);

    void deactivateAccount(String userId);

    void reactivateAccount(String userId);

    void deleteAccount(String userId);
}
