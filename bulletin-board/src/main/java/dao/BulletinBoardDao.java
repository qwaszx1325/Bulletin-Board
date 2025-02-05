package dao;

import java.util.List;

import entity.BulletinBoard;

public interface BulletinBoardDao {

	public List<BulletinBoard> findAllBulletinBoards(int page);
	public BulletinBoard createBulletinBoard(BulletinBoard bulletinBoard);
	public BulletinBoard updateBulletinBoard(BulletinBoard bulletinBoard);
	public boolean deleteBulletinBoaed(Integer id);
	
}
