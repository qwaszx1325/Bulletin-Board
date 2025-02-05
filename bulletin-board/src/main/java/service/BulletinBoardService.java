package service;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import entity.BulletinBoard;

@Service
public interface BulletinBoardService {

	public List<BulletinBoard> findAllBulletinBoards(int page);
	public BulletinBoard createBulletinBoard(BulletinBoard bulletinBoard);
	public BulletinBoard updateBulletinBoard(BulletinBoard bulletinBoard);
	public boolean deleteBulletinBoaed(Integer id);
}
