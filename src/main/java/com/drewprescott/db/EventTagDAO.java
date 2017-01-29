package com.drewprescott.db;

import com.drewprescott.core.EventTagEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class EventTagDAO extends AbstractDAO<EventTagEntity> {
    public EventTagDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<EventTagEntity> findById(Long id) {
        System.out.println("find by id: " + id);
        return Optional.ofNullable(get(id));
    }

    public EventTagEntity create(EventTagEntity eventTagEntity) {
        return persist(eventTagEntity);
    }
}
