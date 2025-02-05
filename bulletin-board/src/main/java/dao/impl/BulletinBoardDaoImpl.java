package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.BulletinBoardDao;
import entity.BulletinBoard;
import jakarta.transaction.Transactional;
import utils.TransactionUtil;

@Repository
@Transactional
public class BulletinBoardDaoImpl implements BulletinBoardDao {

	
	@Autowired
	private TransactionUtil transactionUtil;
	
	@Override
	public List<BulletinBoard> findAllBulletinBoards(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BulletinBoard createBulletinBoard(BulletinBoard bulletinBoard) {
		
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

	

}
