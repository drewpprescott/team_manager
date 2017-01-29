package com.drewprescott.db;

import com.drewprescott.core.RoleEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class RoleDAO extends AbstractDAO<RoleEntity> {
    public RoleDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<RoleEntity> findById(Long id) {
        System.out.println("find by id: " + id);
        return Optional.ofNullable(get(id));
    }

    public RoleEntity create(RoleEntity roleEntity) {
        return persist(roleEntity);
    }
}
