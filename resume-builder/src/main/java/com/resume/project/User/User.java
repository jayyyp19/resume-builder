package com.resume.project.User;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long phoneNumber;
    private String fullName;
    private String address;
    private Long age;
    private String country;
    private String state;
    private String city;
    private Boolean isDeleted = false;
}
