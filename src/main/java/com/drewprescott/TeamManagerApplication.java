package com.drewprescott;

import com.drewprescott.core.*;
import com.drewprescott.db.*;
import com.drewprescott.resources.*;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;


public class TeamManagerApplication extends Application<TeamManagerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TeamManagerApplication().run(args);
    }

    @Override
    public String getName() {
        return "TeamManager";
    }

    @Override
    public void initialize(final Bootstrap<TeamManagerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final TeamManagerConfiguration configuration,
                    final Environment environment) {
        final EventDAO eventDAO = new EventDAO(hibernate.getSessionFactory());
        final EventTagDAO eventTagDAO = new EventTagDAO(hibernate.getSessionFactory());
        final RoleDAO roleDAO = new RoleDAO(hibernate.getSessionFactory());
        final SportDAO sportDAO = new SportDAO(hibernate.getSessionFactory());
        final TeamDAO teamDAO = new TeamDAO(hibernate.getSessionFactory());
        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());

        final EventResource eventResource = new EventResource(eventDAO);
        final EventTagResource eventTagResource = new EventTagResource(eventTagDAO);
        final RoleResource roleResource = new RoleResource();
        final SportResource sportResource = new SportResource();
        final TeamResource teamResource = new TeamResource();
        final UserResource userResource = new UserResource(userDAO);
        environment.jersey().register(eventResource);
        environment.jersey().register(eventTagResource);
        environment.jersey().register(roleResource);
        environment.jersey().register(sportResource);
        environment.jersey().register(teamResource);
        environment.jersey().register(userResource);
    }

    private final HibernateBundle<TeamManagerConfiguration> hibernate = new HibernateBundle<TeamManagerConfiguration>(
            EventEntity.class,
            EventEventRelEntity.class,
            EventTagEntity.class,
            EventTagRelEntity.class,
            RoleEntity.class,
            SportEntity.class,
            TeamEntity.class,
            UserEntity.class,
            UserTeamRelEntity.class)
    {
        @Override
        public DataSourceFactory getDataSourceFactory(TeamManagerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
}
