package tn.esprithub.Entities;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String  firstName;
    private final String  lastName;
    private final String  email;
    private final String  password;
    private  final Gender gender;
    private  final Long phone ;
    private  final  String address;
    private  final  String role;
    private  final Date createdAt;


}
