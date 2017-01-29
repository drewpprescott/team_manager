package com.drewprescott.db;

import com.drewprescott.core.EventTagRelEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class EventTagRelDAO extends AbstractDAO<EventTagRelEntity> {
    public EventTagRelDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<EventTagRelEntity> findById(Long id) {
        System.out.println("find by id: " + id);
        return Optional.ofNullable(get(id));
    }

    public EventTagRelEntity create(EventTagRelEntity eventTagRelEntity) {
        return persist(eventTagRelEntity);
    }
}
