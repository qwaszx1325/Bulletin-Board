package com.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.wls.shaded.org.apache.regexp.recompile;
import com.test.dao.BulletinBoardDao;
import com.test.dto.PageResult;
import com.test.entity.BulletinBoard;
import com.test.service.BulletinBoardService;

@Service
public class BulletinBoardServiceImpl implements BulletinBoardService{
	
	@Autowired
	private BulletinBoardDao bulletinBoardDao;
	
	private final int PAGE_SIZE = 5;
	private final int DEFAULT_PAGE = 1;
	
	@Override
	public PageResult<BulletinBoard> findAllBulletinBoards(int page) {
		PageResult<BulletinBoard> pageResult = new PageResult<BulletinBoard>(); 
		List<BulletinBoard> allBulletinBoards = new ArrayList<BulletinBoard>();
		long totalCount = bulletinBoardDao.getTotalCount();
		
		int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);
		if(page > totalPages) {
			page = DEFAULT_PAGE;
			allBulletinBoards = bulletinBoardDao.findAllBulletinBoards(page, PAGE_SIZE);
		}else {
			allBulletinBoards = bulletinBoardDao.findAllBulletinBoards(page, PAGE_SIZE);
		}
		pageResult.setData(allBulletinBoards);
		pageResult.setTotalCount(totalCount);
		pageResult.setCurrentPage(page);
		pageResult.setTotalPages(totalPages);
		return pageResult;
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
	public boolean deleteBulletinBoard(Integer id) {
		return bulletinBoardDao.deleteBulletinBoaed(id);
	}

	@Override
	public BulletinBoard finBoardById(Integer id) {
		return bulletinBoardDao.finBoardById(id);
	}
}
