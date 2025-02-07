package com.test.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("detail/{id}")
	public String getBoardDetail(@PathVariable("id") Integer id, Model model) {
		BulletinBoard finBoardById = bulletinBoardService.finBoardById(id);
		model.addAttribute("bulletinBoard",finBoardById);
		return "detail";
	}
	
	@GetMapping("/add")
	public String addPage() {
		return "add";
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
	public String createBulletinBoard(@RequestParam("title") String title,
		    @RequestParam("publisher") String publisher,
		    @RequestParam("startDate") String startDate,
		    @RequestParam("endDate") String endDate,
		    @RequestParam("content") String content) {
		BulletinBoard board = new BulletinBoard();
	    board.setTitle(title);
	    board.setPublisher(publisher);
	    board.setStartDate(LocalDate.parse(startDate));
	    board.setEndDate(LocalDate.parse(endDate));
	    board.setContent(content);
	    board.setPublishDate(LocalDate.now());
	    
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
