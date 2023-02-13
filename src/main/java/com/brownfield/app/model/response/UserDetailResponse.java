package com.brownfield.app.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
}
