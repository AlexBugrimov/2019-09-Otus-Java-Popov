package ru.otus.hw11.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.api.dao.UserDao;
import ru.otus.hw11.api.model.User;
import ru.otus.hw11.api.sessionmanager.SessionManager;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.saveUser(user);
                sessionManager.commitSession();

                logger.info("saved user: {}", userId);
                return userId;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new UserServiceException(e);
            }
        }
    }


    @Override
    public Optional<User> getUser(long id) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> userOptional = userDao.findById(id);

                logger.info("loaded user: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                logger.error(e.getMessage(), e);
            }
            return Optional.empty();
        }
    }

}
