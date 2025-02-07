package com.test.service;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import com.test.dto.PageResult;
import com.test.entity.BulletinBoard;

public interface BulletinBoardService {

	public PageResult<BulletinBoard> findAllBulletinBoards(int page);
	public BulletinBoard createBulletinBoard(BulletinBoard bulletinBoard);
	public BulletinBoard updateBulletinBoard(BulletinBoard bulletinBoard);
	public boolean deleteBulletinBoard(Integer id);
	
}
