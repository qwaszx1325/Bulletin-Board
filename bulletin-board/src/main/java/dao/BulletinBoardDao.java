package dao;

import java.util.List;

import entity.BulletinBoard;

public interface BulletinBoardDao {

	public List<BulletinBoard> findAllBulletinBoards(int page,int pageSize);
	public BulletinBoard createBulletinBoard(BulletinBoard bulletinBoard);
	public BulletinBoard updateBulletinBoard(BulletinBoard bulletinBoard);
	public boolean deleteBulletinBoaed(Integer id);
	public long getTotalCount();
	
}
