package jblog.repository;

import java.util.Map;

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

	public UserVo findByIdAndPassword(String id, String password) {
		return sqlSession.selectOne(
				"user.findByIdAndPassword", 
				Map.of("id", id, "password", password));
	}	
}
