package com.drewprescott.api;


import com.drewprescott.core.RoleEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private long id;
    private String role;

    public Role() {
        // Jackson deserialization
    }

    public Role(long id, String role) {
        this.id = id;
        this.role = role;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    static public Role mapToRole(RoleEntity roleEntity) {
        Role role = new Role(
                roleEntity.getId(),
                roleEntity.getRole()
        );
        return role;
    }

    static public List<Role> mapToRoleList(List<RoleEntity> roleEntityList) {
        List<Role> roleList = new ArrayList<Role>();
        for (RoleEntity roleEntity: roleEntityList) {
            roleList.add(mapToRole(roleEntity));
        }
        return roleList;
    }
}
