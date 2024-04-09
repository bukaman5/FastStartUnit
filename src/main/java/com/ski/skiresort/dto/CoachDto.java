package com.ski.skiresort.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachDto {
    private Long id;
    private String fullName;
    private String category;
    private String dateOfBirth;
    private String gender;
    private String photo;


}
