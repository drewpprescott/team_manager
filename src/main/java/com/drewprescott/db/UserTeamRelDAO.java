package com.drewprescott.db;

import com.drewprescott.core.UserTeamRelEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class UserTeamRelDAO extends AbstractDAO<UserTeamRelEntity> {
    public UserTeamRelDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<UserTeamRelEntity> findById(Long id) {
        System.out.println("find by id: " + id);
        return Optional.ofNullable(get(id));
    }

    public UserTeamRelEntity create(UserTeamRelEntity userTeamRelEntity) {
        return persist(userTeamRelEntity);
    }
}
