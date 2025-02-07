package com.test.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionUtil {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@FunctionalInterface
	public interface TransactionCallback<T> {
		T execute(Session session);
	}
	
	public <T> T executeInTransaction(TransactionCallback<T> callback) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            T result = callback.execute(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
	
	
}
