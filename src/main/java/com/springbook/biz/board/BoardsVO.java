package com.springbook.biz.board;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="boards")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardsVO {
	@XmlElement(name="board")
	private List<BoardVO> boards;
	
	public List<BoardVO> getBoards() {
		return boards;
	}
	
	public void setBoards(List<BoardVO> boards) {
		this.boards = boards;
	}
}
