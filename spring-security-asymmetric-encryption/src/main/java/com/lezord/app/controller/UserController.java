package com.lezord.app.controller;


import com.lezord.app.dto.request.ChangePasswordRequestDTO;
import com.lezord.app.dto.request.ProfileUpdateRequestDTO;
import com.lezord.app.entity.User;
import com.lezord.app.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService service;


    @PutMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfile(
        @RequestBody
        @Valid
        ProfileUpdateRequestDTO request,
        Authentication authentication
    ){
        this.service.updateProfileInfo(request, getUserId(authentication));
    }


    @PostMapping("/me/password")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void changePassword(
            @RequestBody
            @Valid
            final ChangePasswordRequestDTO request,
            final Authentication authentication) {
        this.service.changePassword(request, getUserId(authentication));
    }

    @PatchMapping("/me/deactivate")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deactivateAccount(final Authentication authentication) {
        this.service.deactivateAccount(getUserId(authentication));
    }

    @PatchMapping("/me/reactivate")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void reactivateAccount(final Authentication authentication) {
        this.service.reactivateAccount(getUserId(authentication));
    }

    @DeleteMapping("/me")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAccount(final Authentication authentication) {
        this.service.deleteAccount(getUserId(authentication));
    }


    private String getUserId(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getId();
    }
}
