package com.drewprescott.db;

import com.drewprescott.core.UserEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<UserEntity> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public void create(UserEntity userEntity) {
        String plainTextPassword = userEntity.getPassword();
        String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
        userEntity.setPassword(hashedPassword);
        persist(userEntity);
    }

    public Optional<UserEntity> findByEmail(UserEntity userEntity) {
        StringBuilder emailBuilder = new StringBuilder("%");
        emailBuilder.append(userEntity.getEmail()).append("%");
        return list(namedQuery("com.drewprescott.core.UserEntity.findByEmail")
                .setParameter("email", emailBuilder.toString())).stream().findFirst();
    }
}
