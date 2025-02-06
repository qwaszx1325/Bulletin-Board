package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import entity.BulletinBoard;
import service.BulletinBoardService;

@Controller
public class BulletinBoardController {

	@Autowired
	private BulletinBoardService bulletinBoardService;
	
	@GetMapping("/home")
	public List<BulletinBoard> getAllBulletinBoards(int page){
		
		return null;
	}
	
	@PostMapping("/createBoard")
	public String createBulletinBoard(@RequestBody BulletinBoard board) {
		bulletinBoardService.createBulletinBoard(board);
		return "redirect:/home";
	}
	
	@PostMapping("/updateBoard")
	public String updateBulletinBoard(@RequestBody BulletinBoard board) {
		bulletinBoardService.updateBulletinBoard(board);
		return "redirect:/home";
	}
	@GetMapping("/deleteBoard")
	public String deleteBulletinBoard(Integer id) {
		bulletinBoardService.deleteBulletinBoaed(id);
		return "redirect:/home";
	}
	
	
	
}
