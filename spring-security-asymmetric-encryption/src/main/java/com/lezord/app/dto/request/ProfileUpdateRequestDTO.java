package com.lezord.app.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileUpdateRequestDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
