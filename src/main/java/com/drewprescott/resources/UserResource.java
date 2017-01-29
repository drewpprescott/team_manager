package com.drewprescott.resources;

import com.drewprescott.api.User;
import com.drewprescott.core.UserEntity;
import com.drewprescott.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.FormParam;
import org.mindrot.jbcrypt.BCrypt;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public User findById(@PathParam("id") LongParam id) {
        return findSafelyById(id.get());
    }

    @POST
    @Path("/create")
    @Timed
    @UnitOfWork
    public boolean addUser(User user) {
        userDAO.create(UserEntity.mapToEntity(user));
        return true;
    }

    @POST
    @Path("/authenticate")
    @Timed
    @UnitOfWork
    public User login(User user) {
        Optional<UserEntity> userEntity = userDAO.findByEmail(UserEntity.mapToEntity(user));
        if (!userEntity.isPresent()) {
            return null;
        }
        User dbUser = User.mapToUser(userEntity.get());
        if (BCrypt.checkpw(user.getPassword(), dbUser.getPassword())) {
            return dbUser;
        }
        return null;
    }

    private User findSafelyById(long userId) {
        return User.mapToUser(userDAO.findById(userId).orElseThrow(() -> new NotFoundException("No such event.")));
    }
}
