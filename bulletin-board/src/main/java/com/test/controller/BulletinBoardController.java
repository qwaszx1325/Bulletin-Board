package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.dto.PageResult;
import com.test.entity.BulletinBoard;
import com.test.service.BulletinBoardService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BulletinBoardController {

	@Autowired
	private BulletinBoardService bulletinBoardService;

	@GetMapping("/")
	public String index() {
		return "redirect:/home";
	}

	@GetMapping("detail/{id}")
	public String getBoardDetail(@PathVariable("id") Integer id, Model model) {
		BulletinBoard finBoardById = bulletinBoardService.finBoardById(id);
		model.addAttribute("bulletinBoard", finBoardById);
		return "detail";
	}

	@GetMapping("/add")
	public String addPage() {
		return "add";
	}

	@GetMapping("/home")
	public String getAllBulletinBoards(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
			Model model) {
		PageResult<BulletinBoard> allBulletinBoards = bulletinBoardService.findAllBulletinBoards(pageNumber);
		model.addAttribute("bulletinBoard", allBulletinBoards.getData());
		model.addAttribute("currentPage", allBulletinBoards.getCurrentPage());
		model.addAttribute("totalPage", allBulletinBoards.getTotalPages());
		model.addAttribute("totalCount", allBulletinBoards.getTotalCount());

		return "home";
	}

	@PostMapping("/createBoard")
	public String createBulletinBoard(@RequestParam("title") String title, @RequestParam("publisher") String publisher,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("content") String content,
			@RequestParam(value = "attachment", required = false) MultipartFile attachment) {
		BulletinBoard board = new BulletinBoard();
		board.setTitle(title);
		board.setPublisher(publisher);
		board.setStartDate(LocalDate.parse(startDate));
		board.setEndDate(LocalDate.parse(endDate));
		board.setContent(content);
		board.setPublishDate(LocalDate.now());

		if (attachment != null && !attachment.isEmpty()) {
			try {
				board.setAttachmentData(attachment.getBytes());
				board.setAttachmentName(attachment.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		bulletinBoardService.createBulletinBoard(board);
		return "redirect:/home";
	}

	@GetMapping("edit/{id}")
	public String editBoard(@PathVariable("id") Integer id, Model model) {
		BulletinBoard finBoardById = bulletinBoardService.finBoardById(id);
		model.addAttribute("bulletinBoard", finBoardById);
		return "edit";

	}

	@PostMapping("/updateBoard")
	public String updateBulletinBoard(@RequestParam("id") Integer id, @RequestParam("title") String title,
			@RequestParam("publisher") String publisher, @RequestParam("publishDate") String publishDate,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("content") String content,
			@RequestParam(value = "attachment", required = false) MultipartFile attachment) {

		BulletinBoard board = bulletinBoardService.finBoardById(id);
		board.setId(id);
		board.setTitle(title);
		board.setPublisher(publisher);
		board.setPublishDate(LocalDate.parse(publishDate));
		board.setStartDate(LocalDate.parse(startDate));
		board.setEndDate(LocalDate.parse(endDate));
		board.setContent(content);
		if (attachment != null && !attachment.isEmpty()) {
			try {
				board.setAttachmentData(attachment.getBytes());
				board.setAttachmentName(attachment.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		bulletinBoardService.updateBulletinBoard(board);
		return "redirect:/home";
	}

	@GetMapping("/deleteBoard/{id}")

	public String deleteBulletinBoard(@PathVariable("id") Integer id) {
		bulletinBoardService.deleteBulletinBoard(id);
		return "redirect:/home";
	}

	@GetMapping("/downloadAttachment/{id}")
	public ResponseEntity<byte[]> downloadAttachment(@PathVariable("id") Integer id) {
		BulletinBoard board = bulletinBoardService.finBoardById(id);

		if (board.getAttachmentData() == null) {
			return ResponseEntity.notFound().build();
		}

		try {
	        String encodedFilename = URLEncoder.encode(board.getAttachmentName(), StandardCharsets.UTF_8.toString())
	                .replace("+", "%20");  // 空格需要特別處理
	                
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, 
	                        "attachment; filename*=UTF-8''" + encodedFilename)
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .body(board.getAttachmentData());
	    } catch (UnsupportedEncodingException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

}
