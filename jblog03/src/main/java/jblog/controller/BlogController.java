package jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jblog.exception.BlogNotFoundException;
import jblog.security.Auth;
import jblog.security.AuthUser;
import jblog.service.BlogService;
import jblog.service.FileUploadService;
import jblog.service.UserService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.vo.UserVo;


@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	private final UserService userService;
	private final BlogService blogService;
	private final FileUploadService fileUploadService;
	private final ServletContext servletContext;
	
	public BlogController(
			UserService userService, 
			BlogService blogService,
			FileUploadService fileUploadService,
			ServletContext servletContext) {
		this.userService = userService;
		this.blogService = blogService;
		this.fileUploadService = fileUploadService;
		this.servletContext = servletContext;
	}
	
	@RequestMapping({"", "/{categoryId}", "/{categoryId}/{postId}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable(value="categoryId") Optional<Long> categoryId,
			@PathVariable(value="postId") Optional<Long> postId,
			Model model
	) {
		UserVo userVo = userService.getUser(id);
		if(userVo == null) {
			throw new BlogNotFoundException("해당 블로그를 찾을 수 없습니다." + id);
		}
		
		List<CategoryVo> categories = blogService.getCategories(id);
		model.addAttribute("categories", categories);	
		
		Map<String, Object> result = fetchPosts(id, categoryId, postId);
		model.addAttribute("posts", result.get("posts"));
		model.addAttribute("selectedPost", result.get("selectedPost"));
		
		BlogVo blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);
		
		return "blog/main";
	}

	private Map<String, Object> fetchPosts(String id, Optional<Long> categoryId, Optional<Long> postId) {
		Map<String, Object> result = new HashMap<>();
		
		if (postId.isPresent()) {
			List<PostVo> posts = blogService.getPosts(id, categoryId.get());
			result.put("posts", posts);
			
			PostVo selectedPost = blogService.getSelectedPost(postId.get());
			result.put("selectedPost", selectedPost);

		} else if (categoryId.isPresent()) {
			List<PostVo> posts = blogService.getPosts(id, categoryId.get());
			result.put("posts", posts);
		} else {
			List<PostVo> posts = blogService.getPosts(id);
			result.put("posts", posts);
		}
	
		return result;
	}
	
	@Auth
	@RequestMapping("/admin/default")
	public String adminDefault(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model) {
		BlogVo blog = blogService.getBlog(authUser.getId());
		model.addAttribute("blog", blog);
		
		return "blog/admin-default";
	}
	
	@Auth
	@RequestMapping(value="/admin/default/update", method=RequestMethod.POST)
	public String updateAdminDefault(
			@AuthUser UserVo authUser,
			@PathVariable("id") String id,
			@ModelAttribute BlogVo blogVo, @RequestParam("file") MultipartFile multipartFile) {
		String profile = fileUploadService.restore(multipartFile);
		if(profile != null) {
			blogVo.setProfile(profile);
		}
		blogService.updateBlog(blogVo);
		servletContext.setAttribute("blog", blogVo);
		
		return "redirect:/" + authUser.getId() + "/admin/default";
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(
			@AuthUser UserVo authUser,
			@PathVariable("id") String id,
			Model model) {
		List<CategoryVo> categories = blogService.getCategories(authUser.getId());
		model.addAttribute("list", categories);
		
		BlogVo blog = blogService.getBlog(authUser.getId());
		model.addAttribute("blog", blog);
		
		return "blog/admin-category";
	}
	
	@Auth
	@PostMapping("/admin/category/add")
	public String addCategory(
			@AuthUser UserVo authUser,
			@PathVariable("id") String id,
			@RequestParam("name") String name,
			@RequestParam("desc") String description) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName(name); 
		categoryVo.setDescription(description);
		categoryVo.setBlogId(authUser.getId());
		
		blogService.addCategory(categoryVo);
		return "redirect:/" + authUser.getId() + "/admin/category";
	}
	
	@Auth
	@PostMapping("/admin/category/delete/{categoryId}")
	public String deleteCategory(
			@AuthUser UserVo authUser,
			@PathVariable("categoryId") Long categoryId) {
		blogService.deleteCategory(categoryId, authUser.getId());
		return "redirect:/" + authUser.getId() + "/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(
			@AuthUser UserVo authUser,
			@PathVariable("id") String id,
			Model model) {
		List<CategoryVo> categories = blogService.getCategories(authUser.getId());
		model.addAttribute("categories", categories);
		
		BlogVo blog = blogService.getBlog(authUser.getId());
		model.addAttribute("blog", blog);
		
		
		return "blog/admin-write";
	}
	
	@Auth
	@PostMapping("/admin/write")
	public String addPost(
			@AuthUser UserVo authUser,
			@PathVariable("id") String id,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(value="category", required=false) Long categoryId) {
		
		PostVo postVo = new PostVo();
		postVo.setTitle(title);
		postVo.setContents(content);
		postVo.setCategoryId(categoryId);
		
		blogService.addPost(postVo);
		return "redirect:/" + authUser.getId();
	}
}
