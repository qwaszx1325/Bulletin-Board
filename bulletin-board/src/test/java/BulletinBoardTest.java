import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.dto.PageResult;
import com.test.entity.BulletinBoard;
import com.test.service.BulletinBoardService;
import com.test.service.impl.BulletinBoardServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BulletinBoardTest {

	@Autowired BulletinBoardService bulletinBoardService;
	
	@Test
	public void testUpdateBoard() {
		BulletinBoard bulletinBoard = new BulletinBoard();
		bulletinBoard.setId(15);
		bulletinBoard.setPublisher("小胖");
		bulletinBoardService.updateBulletinBoard(bulletinBoard);
	}
	
//	@Test
//	public void testDeleteBoard() {
//		boolean deleteBulletinBoaed = bulletinBoardService.deleteBulletinBoaed(16);
//		System.out.println(deleteBulletinBoaed);
//	}
		
	
//	@Test
//	public void testGetAllBoard() {
//		PageResult<BulletinBoard> allBulletinBoards = bulletinBoardService.findAllBulletinBoards(4);
//		List<BulletinBoard> data = allBulletinBoards.getData();
//		for(BulletinBoard board: data) {
//            System.out.println("公告 ID: " + board.getId());
//            System.out.println("標題: " + board.getTitle());
//            System.out.println("發布者: " + board.getPublisher());
//            System.out.println("開始時間: " + board.getStartDate());
//            System.out.println("結束時間: " + board.getEndDate());
//            System.out.println("內容: " + board.getContent());
//            System.out.println("--------------------");
//        }
//		
//		System.out.println("CurrentPage: "+allBulletinBoards.getCurrentPage());
//		System.out.println("TotalCount: "+allBulletinBoards.getTotalCount());
//		System.out.println("TotalPages: " + allBulletinBoards.getTotalPages());
//		
//	}
	
//	@Test 
//	public void testCreateBoard() {
//		BulletinBoard bulletinBoard = new BulletinBoard();
//		bulletinBoard.setTitle("2024年度系統維護通知");
//	    bulletinBoard.setPublisher("系統管理員");
//	    bulletinBoard.setContent("親愛的用戶您好，\n系統將於2024/3/1進行年度維護更新，維護期間系統將暫停服務，造成不便敬請見諒。");
//	    
//	    // 設置開始和結束時間
//	    LocalDateTime startTime = LocalDateTime.of(2024, 2, 25, 0, 0, 0);
//	    LocalDateTime endTime = LocalDateTime.of(2024, 3, 5, 23, 59, 59);
//	    bulletinBoard.setStartDate(startTime);
//	    bulletinBoard.setEndDate(endTime);
//
//	    // 創建公告
//	    BulletinBoard createdBoard = bulletinBoardService.createBulletinBoard(bulletinBoard);
//	    
//
//		BulletinBoard bulletinBoard2 = bulletinBoardService.createBulletinBoard(bulletinBoard);
//		
//		System.out.println(bulletinBoard2.getId());
//	}
}
