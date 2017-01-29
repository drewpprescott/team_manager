package com.drewprescott.db;

import com.drewprescott.core.EventEventRelEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class EventEventRelDAO extends AbstractDAO<EventEventRelEntity> {
    public EventEventRelDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<EventEventRelEntity> findById(Long id) {
        System.out.println("find by id: " + id);
        return Optional.ofNullable(get(id));
    }

    public EventEventRelEntity create(EventEventRelEntity eventEventRelEntity) {
        return persist(eventEventRelEntity);
    }
}
