package service;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import dto.PageResult;
import entity.BulletinBoard;

public interface BulletinBoardService {

	public PageResult<BulletinBoard> findAllBulletinBoards(int page);
	public BulletinBoard createBulletinBoard(BulletinBoard bulletinBoard);
	public BulletinBoard updateBulletinBoard(BulletinBoard bulletinBoard);
	public boolean deleteBulletinBoaed(Integer id);
	
}
