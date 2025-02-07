package com.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.dto.PageResult;
import com.test.entity.BulletinBoard;
import com.test.service.BulletinBoardService;

@Controller
public class BulletinBoardController {

	@Autowired
	private BulletinBoardService bulletinBoardService;
	
	@GetMapping("/")
	public String index() {
	    return "index";  
	}
	
	@GetMapping("/home")
	public String getAllBulletinBoards(@RequestParam(name ="pageNumber", defaultValue = "1") int pageNumber,
			Model model){
		PageResult<BulletinBoard> allBulletinBoards = bulletinBoardService.findAllBulletinBoards(pageNumber);
		model.addAttribute("bulletinBoard",allBulletinBoards.getData());
		model.addAttribute("currentPage",allBulletinBoards.getCurrentPage());
		model.addAttribute("totalPage", allBulletinBoards.getTotalPages());
		model.addAttribute("totalCount", allBulletinBoards.getTotalCount());
		
		return "home";
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
		bulletinBoardService.deleteBulletinBoard(id);
		return "redirect:/home";
	}
	
	
	
}
