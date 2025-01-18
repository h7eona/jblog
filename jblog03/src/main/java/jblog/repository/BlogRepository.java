package jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Repository
public class BlogRepository {
	private final SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public BlogVo findBlog(String id) {
		return sqlSession.selectOne("blog.find-blog", id);
	}

	public void updateBlog(BlogVo vo) {
		sqlSession.update("blog.update-blog", vo);
	}
	
	public void insertBlog(BlogVo vo) {
		sqlSession.insert("blog.insertDefault", vo);
	}

	public void insertCategory(CategoryVo vo) {
		sqlSession.insert("category.insert", vo);
	}

	public List<CategoryVo> findAllCategories(String id) {
		return sqlSession.selectList("category.find", id);
	}
	
	public void deleteCategory(Long categoryId) {
		sqlSession.delete("category.delete", categoryId);
	}

	public void insertPost(PostVo vo) {
		sqlSession.insert("post.insert", vo);
	}

	public List<PostVo> findAllPosts(String id) {
		return sqlSession.selectList("post.findAllPosts", id);
	}

	public PostVo findByPostId(Long id) {
		return sqlSession.selectOne("post.findByPostId", id);
	}

	public List<PostVo> findByCategories(String id, Long categoryId) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("categoryId", categoryId);
		
		return sqlSession.selectList("post.findByCategoryAndId", params);
	}

	public Long findDefaultId(String id) {
		return sqlSession.selectOne("post.findDefaultId", id);
	}

	public void updateCategoryToDefault(Long defaultId, Long categoryId) {
		Map<String, Object> params = new HashMap<>();
		params.put("defaultCategoryId", defaultId);
		params.put("oldCategoryId", categoryId);
		
		sqlSession.update("post.updateToDefault", params);
	}
}
