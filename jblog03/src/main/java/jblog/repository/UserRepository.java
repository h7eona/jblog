package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.UserVo;

@Repository
public class UserRepository {
	private final SqlSession sqlSession;
	
	public UserRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}
	
	public UserVo findById(String id) {
		return sqlSession.selectOne("user.findById", id);
	}
	
	public UserVo findByName(String name) {
		return sqlSession.selectOne("user.findByName", name);
	}
}
