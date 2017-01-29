package com.drewprescott.core;

import com.drewprescott.api.Role;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "roleId", fetch = FetchType.LAZY)
    private Set<UserTeamRelEntity> userTeamRels;

    public RoleEntity() {
        //Serialization
    }

    public RoleEntity(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (id != that.id) return false;
        return role.equals(that.role);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + role.hashCode();
        return result;
    }

    static public RoleEntity mapToEntity(Role role) {
        RoleEntity roleEntity = new RoleEntity(
                role.getRole()
        );
        return roleEntity;
    }

    static public List<RoleEntity> mapToEntityList(List<Role> roleList) {
        List<RoleEntity> roleEntityList = new ArrayList<RoleEntity>();
        for (Role role: roleList) {
            roleEntityList.add(mapToEntity(role));
        }
        return roleEntityList;
    }
}
