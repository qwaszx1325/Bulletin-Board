package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.BulletinBoardDao;
import entity.BulletinBoard;
import service.BulletinBoardService;

public class BulletinBoardServiceImpl implements BulletinBoardService{
	
	@Autowired
	private BulletinBoardDao bulletinBoardDao;
	
	@Override
	public List<BulletinBoard> findAllBulletinBoards(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BulletinBoard createBulletinBoard(BulletinBoard bulletinBoard) {
		return bulletinBoardDao.createBulletinBoard(bulletinBoard);
	}

	@Override
	public BulletinBoard updateBulletinBoard(BulletinBoard bulletinBoard) {
		return bulletinBoardDao.updateBulletinBoard(bulletinBoard);
	}

	@Override
	public boolean deleteBulletinBoaed(Integer id) {
		return bulletinBoardDao.deleteBulletinBoaed(id);
	}

}
