package ru.otus.hw11.hibernate.dao;


import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.api.dao.UserDao;
import ru.otus.hw11.api.dao.UserDaoException;
import ru.otus.hw11.api.model.User;
import ru.otus.hw11.api.sessionmanager.SessionManager;
import ru.otus.hw11.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Optional;

public class UserDaoHibernate implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);

    private final SessionManagerHibernate sessionManager;

    public UserDaoHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            return Optional.ofNullable(sessionManager.getCurrentSession().getHibernateSession().find(User.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public long saveUser(User user) {
        if (user==null) throw new IllegalArgumentException("User is null");
        try {
            Session hibernateSession = sessionManager.getCurrentSession().getHibernateSession();
            if (user.getId() > 0) {
                hibernateSession.merge(user);
            } else {
                hibernateSession.persist(user);
            }
            return user.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return this.sessionManager;
    }
}
