package com.example.restapp.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MasterDTO {

    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @JsonIgnore
    private long studioId;

    private StudioDTO studio;

    @NotEmpty
    @NotNull
    private String specialization;

    @NotEmpty
    private int experienceYears;
}
