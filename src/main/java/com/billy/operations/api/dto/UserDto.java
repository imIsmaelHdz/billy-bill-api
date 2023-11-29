package com.billy.operations.api.dto;

import com.billy.operations.api.model.JobNationality;
import com.billy.operations.api.model.Profile;
import com.billy.operations.api.model.Tax;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {
    private UUID customId;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer birthYear;
    private String RFC;
    private JobNationality jobNationality;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Set<Tax> regimes;
}
