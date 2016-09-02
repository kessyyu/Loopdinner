package com.loopdiner.comments;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service(value="commentService")
public class CommentService {
	
	@Resource(name="commentDao")
	private CommentDao commentDao;

	public CommentService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<CommentResult> getCommentMsg(CommentQueryCond commentQueryCond){
		return commentDao.getCommentMsg(commentQueryCond);
	}

}
