package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.BoardsVO;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public BoardsVO dataTransform(BoardVO boardVO) {
		boardVO.setSearchCondition("TITLE");
		boardVO.setSearchKeyword("");
		
		List<BoardVO> boards = boardService.getBoards(boardVO);
		BoardsVO boardsVO = new BoardsVO();
		boardsVO.setBoards(boards);
		
		return boardsVO;
	}
	
	// 글 등록
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO boardVO) throws IOException {
		// 파일 업로드 처리
		MultipartFile uploadFile = boardVO.getUploadFile();
		
		if (!uploadFile.isEmpty()) {
			uploadFile.transferTo(new File("D:/" + uploadFile.getOriginalFilename()));
		}
		
		boardService.insertBoard(boardVO);
		
		return "getBoards.do";
	}
	
	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO boardVO) {
		boardService.updateBoard(boardVO);
		
		return "getBoards.do";
	}
	
	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO boardVO) {
		boardService.deleteBoard(boardVO);
		
		return "getBoards.do";
	}
	
	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO boardVO, Model model) {
		model.addAttribute("board", boardService.getBoard(boardVO));
		
		return "getBoard.jsp";
	}
	
	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<>();
			
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
			
		return conditionMap;
	}
	
	// 글 목록 검색
	@RequestMapping("/getBoards.do")
	public String getBoards(BoardVO boardVO, Model model) {
		if (boardVO.getSearchCondition() == null) {
			boardVO.setSearchCondition("TITLE");
		}
		
		if (boardVO.getSearchKeyword() == null) {
			boardVO.setSearchKeyword("");
		}
		
		model.addAttribute("boards", boardService.getBoards(boardVO)); // Model 정보 저장
		
		return "getBoards.jsp"; // View 이름 리턴
	}
}
