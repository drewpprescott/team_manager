package com.drewprescott.db;

import com.drewprescott.core.TeamEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class TeamDAO extends AbstractDAO<TeamEntity> {
    public TeamDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<TeamEntity> findById(Long id) {
        System.out.println("find by id: " + id);
        return Optional.ofNullable(get(id));
    }
}
