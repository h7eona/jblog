package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Service
public class BlogService {
	
	private final BlogRepository blogRepository;
	
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	public BlogVo getBlog(String id) {
		return blogRepository.findBlog(id);
	}
	
	public void updateBlog(BlogVo vo) {
		blogRepository.updateBlog(vo);
	}
	
	public void addCategory(CategoryVo vo) {
		blogRepository.insertCategory(vo);
	}
	
	public void deleteCategory(Long categoryId, String id) {
		Long defaultId = blogRepository.findDefaultId(id);
		blogRepository.updateCategoryToDefault(defaultId, categoryId);
		blogRepository.deleteCategory(categoryId);
	}

	public List<CategoryVo> getCategories(String id) {
		List<CategoryVo> categories = blogRepository.findAllCategories(id);
		for (CategoryVo category : categories) {
			if("미지정".equals(category.getName())) {
				category.setName("기타");
			}
		}

		return categories;
	}

	public void addPost(PostVo vo) {
		blogRepository.insertPost(vo);
	}
	
	public List<PostVo> getPosts(String id) {
		return blogRepository.findAllPosts(id);
	}

	public List<PostVo> getPosts(String id, Long categoryId) {
		return blogRepository.findByCategories(id, categoryId);
	}
	
	public PostVo getSelectedPost(Long id) {
		return blogRepository.findByPostId(id);
	}
}
