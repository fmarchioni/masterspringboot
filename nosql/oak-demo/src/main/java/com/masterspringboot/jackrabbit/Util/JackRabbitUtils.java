package com.masterspringboot.jackrabbit.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;

public class JackRabbitUtils {
    static Logger logger = LoggerFactory.getLogger(JackRabbitUtils.class);

    public static Session getSession(Repository repo) {
        try {
            if (repo != null)
                return repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
        } catch (LoginException le) {
            logger.error("Exception caught: " + le.getLocalizedMessage());
            le.printStackTrace();
        } catch (RepositoryException re) {
            logger.error("Exception caught: " + re.getLocalizedMessage());
            re.printStackTrace();
        }
        return null;
    }

    public static void cleanUp(Session session) {
        if (session != null) {
            session.logout();
        }
    }
}
