package com.drewprescott.api;


import com.drewprescott.core.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String password;
    private Instant createdTimeStamp;

    public User() {
        // Jackson deserialization
    }

    public User(Long id, String firstName, String lastName, String email, String homePhone, String mobilePhone, String password, Instant createdTimeStamp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.password = password;
        this.createdTimeStamp = createdTimeStamp;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getHomePhone() {
        return homePhone;
    }

    @JsonProperty
    public String getMobilePhone() {
        return mobilePhone;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public Instant getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", password='" + password + '\'' +
                ", createdTimeStamp=" + createdTimeStamp +
                '}';
    }

        static public User mapToUser(UserEntity userEntity) {
        User user = new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getHomePhone(),
                userEntity.getMobilePhone(),
                userEntity.getPassword(),
                userEntity.getCreatedTimeStamp().toInstant()
        );
        return user;
    }

    static public List<User> mapToUserList(List<UserEntity> userEntityList) {
        List<User> userList = new ArrayList<User>();
        for (UserEntity userEntity: userEntityList) {
            userList.add(mapToUser(userEntity));
        }
        return userList;
    }
}
