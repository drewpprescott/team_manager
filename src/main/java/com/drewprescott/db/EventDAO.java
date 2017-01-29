package com.drewprescott.db;

import com.drewprescott.core.EventEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class EventDAO extends AbstractDAO<EventEntity> {
    public EventDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<EventEntity> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public EventEntity create(EventEntity eventEntity) {
        return persist(eventEntity);
    }

    public List<EventEntity> findAll() {
        return list(namedQuery("com.drewprescott.core.EventEntity.findAll"));
    }

    public List<EventEntity> findByTitle(String title) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(title).append("%");
        return list(namedQuery("com.drewprescott.core.EventEntity.findByTitle")
                .setParameter("title", builder.toString()));
    }
}
