import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dto.PageResult;
import entity.BulletinBoard;
import service.BulletinBoardService;
import service.impl.BulletinBoardServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BulletinBoardTest {

	@Autowired BulletinBoardService bulletinBoardService;
	
	@Test
	public void testGetAllBoard() {
		PageResult<BulletinBoard> allBulletinBoards = bulletinBoardService.findAllBulletinBoards(2);
		List<BulletinBoard> data = allBulletinBoards.getData();
		for(BulletinBoard board: data) {
            System.out.println("公告 ID: " + board.getId());
            System.out.println("標題: " + board.getTitle());
            System.out.println("發布者: " + board.getPublisher());
            System.out.println("開始時間: " + board.getStartDate());
            System.out.println("結束時間: " + board.getEndDate());
            System.out.println("內容: " + board.getContent());
            System.out.println("--------------------");
        }
		
		System.out.println("CurrentPage: "+allBulletinBoards.getCurrentPage());
		System.out.println("TotalCount: "+allBulletinBoards.getTotalCount());
		System.out.println("TotalPages: " + allBulletinBoards.getTotalPages());
		
	}
}
