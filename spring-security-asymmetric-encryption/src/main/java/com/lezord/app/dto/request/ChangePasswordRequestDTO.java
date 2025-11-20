package com.lezord.app.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordRequestDTO {
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
}
