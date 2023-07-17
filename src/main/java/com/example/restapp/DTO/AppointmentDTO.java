package com.example.restapp.DTO;

import com.example.restapp.models.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentDTO {

    private long id;
    @NotEmpty(message = "Your name can not be empty")
    @NotNull(message = "Your name can not be empty")
    private String clientName;

    @NotEmpty(message = "Master's name can not be empty")
    @NotNull(message = "Your name can not be empty")
    private String masterName;

    @NotEmpty
    private Status status;

    private StudioDTO studio;
}

