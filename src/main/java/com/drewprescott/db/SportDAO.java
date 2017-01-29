package com.drewprescott.db;

import com.drewprescott.core.SportEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class SportDAO extends AbstractDAO<SportEntity> {
    public SportDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<SportEntity> findById(Long id) {
        System.out.println("find by id: " + id);
        return Optional.ofNullable(get(id));
    }
}
