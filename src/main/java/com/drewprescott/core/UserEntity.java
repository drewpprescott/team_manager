package com.drewprescott.core;

import com.drewprescott.api.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(
                name = "com.drewprescott.core.UserEntity.findByEmail",
                query = "SELECT u FROM UserEntity u "
                        + "where u.email like :email"
        )
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "home_phone", nullable = true)
    private String homePhone;

    @Column(name = "mobile_phone", nullable = true)
    private String mobilePhone;

    @Column(name="password", nullable = true)
    private String password;

    @Column(name="created_ts", nullable = false, insertable = false, updatable = false)
    private Timestamp createdTimeStamp;

    @OneToMany(mappedBy = "createdUserId", fetch = FetchType.LAZY)
    private Set<EventEntity> events;

    @OneToMany(mappedBy = "createdUserId", fetch = FetchType.LAZY)
    private Set<EventEventRelEntity> eventRelationships;

    @OneToMany(mappedBy = "createdUserId", fetch = FetchType.LAZY)
    private Set<EventTagEntity> eventTags;

    @OneToMany(mappedBy = "createdUserId", fetch = FetchType.LAZY)
    private Set<EventTagRelEntity> eventTagRels;

    @OneToMany(mappedBy = "createdUserId", fetch = FetchType.LAZY)
    private Set<TeamEntity> teams;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<UserTeamRelEntity> users;

    public UserEntity() {
        //Serialization
    }

    public UserEntity(String firstName, String lastName, String email, String homePhone, String mobilePhone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return createdTimeStamp != null ? createdTimeStamp.equals(that.createdTimeStamp) : that.createdTimeStamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (createdTimeStamp != null ? createdTimeStamp.hashCode() : 0);
        return result;
    }

    static public UserEntity mapToEntity(User user) {
        UserEntity userEntity = new UserEntity(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getHomePhone(),
                user.getMobilePhone(),
                user.getPassword()
        );
        return userEntity;
    }

    static public List<UserEntity> mapToEntityList(List<User> userList) {
        List<UserEntity> userEntityList = new ArrayList<UserEntity>();
        for (User user: userList) {
            userEntityList.add(mapToEntity(user));
        }
        return userEntityList;
    }
}
