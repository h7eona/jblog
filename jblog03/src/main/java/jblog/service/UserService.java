package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.repository.UserRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.UserVo;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final BlogRepository blogRepository;
	
	public UserService(UserRepository userRepository, BlogRepository blogRepository) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
	}
	
	public void join(UserVo vo) {
		userRepository.insert(vo);
		
		createDefaultBlog(vo.getId());
		createDefaultCategory(vo.getId());
	}

	private void createDefaultCategory(String userId) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName("미지정");
		categoryVo.setDescription("기본 카테고리입니다.");
		categoryVo.setBlogId(userId);
		
		blogRepository.insertCategory(categoryVo);
	}
	
	private void createDefaultBlog(String userId) {
		BlogVo blogVo =new BlogVo();
		blogVo.setId(userId);
		blogVo.setTitle("기본 블로그입니다.");
		blogVo.setProfile("/assets/images/spring-logo.jpg");
		
		blogRepository.insertBlog(blogVo);
	}

	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}
}
