package com.immunopass.controller.request;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@NotNull
public class VerifyOtpRequest {
    @NotNull
    private String otp;
    @NotNull
    private String identifier;
}
