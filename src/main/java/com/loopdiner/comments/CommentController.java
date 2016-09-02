package com.loopdiner.comments;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Comment")
public class CommentController {
	
	@Resource(name = "commentService")
	private CommentService commentService;

	public CommentController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/getCommentMsg", method = RequestMethod.POST)
    @ResponseBody
	public CommentResultList getCommentMsg(@RequestBody CommentQueryCond commentQueryCond){
		CommentResultList commentResultList = new CommentResultList();
		commentResultList.setComments(commentService.getCommentMsg(commentQueryCond));
		return commentResultList;
	}

}
