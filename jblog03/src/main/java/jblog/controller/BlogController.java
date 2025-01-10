package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {
	@RequestMapping({"/{id}", "/{id}/{categoryId}", "/{id}/{categoryId}/{postId}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("categoryId") Long categoryId,
			@PathVariable("postId") Long postId) {
		return "blog/main";
	}
}
