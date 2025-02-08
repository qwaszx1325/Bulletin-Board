package com.test.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dao.BulletinBoardDao;
import com.test.entity.BulletinBoard;
import jakarta.transaction.Transactional;
import com.test.utils.TransactionUtil;

@Repository
@Transactional
public class BulletinBoardDaoImpl implements BulletinBoardDao {

	
	@Autowired
	private TransactionUtil transactionUtil;
	
	@Override
	public List<BulletinBoard> findAllBulletinBoards(int page ,int pageSize) {
		
		return transactionUtil.executeInTransaction(session -> {
			Query<BulletinBoard> query = session.createQuery(
		            "FROM BulletinBoard ORDER BY publishDate DESC", 
		            BulletinBoard.class
		        );
		        
		        query.setFirstResult((page-1) * pageSize);
		        query.setMaxResults(5);
		        
		        return query.list();
		});
	}

	@Override
	public BulletinBoard createBulletinBoard(BulletinBoard bulletinBoard) {
		bulletinBoard.setPublishDate(LocalDate.now());
		return transactionUtil.executeInTransaction(session ->{
			session.persist(bulletinBoard);
			return bulletinBoard;
		});
	}

	@Override
	public BulletinBoard updateBulletinBoard(BulletinBoard bulletinBoard) {
		return transactionUtil.executeInTransaction(session ->{
			return session.merge(bulletinBoard);
		});
	}

	@Override
	public boolean deleteBulletinBoaed(Integer id) {
		return transactionUtil.executeInTransaction(session -> {
			BulletinBoard board = session.get(BulletinBoard.class, id);
			if(board !=null) {
				session.remove(board);
				return true;
			}
			return false;
		});
		
	}

	@Override
	public long getTotalCount() {
		return transactionUtil.executeInTransaction(session -> {
			Query<Long> query = session.createQuery(
		            "SELECT COUNT(*) FROM BulletinBoard", 
		            Long.class
		        );
		        return query.uniqueResult();
		});
	}

	@Override
	public BulletinBoard finBoardById(Integer id) {
		return transactionUtil.executeInTransaction(session -> {
	        return session.get(BulletinBoard.class, id);
	    });
	}

	

}
