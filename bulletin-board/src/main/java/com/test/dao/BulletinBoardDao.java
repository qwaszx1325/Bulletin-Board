package com.test.dao;

import java.util.List;

import com.test.entity.BulletinBoard;

public interface BulletinBoardDao {

	public List<BulletinBoard> findAllBulletinBoards(int page,int pageSize);
	public BulletinBoard finBoardById(Integer id);
	public BulletinBoard createBulletinBoard(BulletinBoard bulletinBoard);
	public BulletinBoard updateBulletinBoard(BulletinBoard bulletinBoard);
	public boolean deleteBulletinBoaed(Integer id);
	public long getTotalCount();
	
}
