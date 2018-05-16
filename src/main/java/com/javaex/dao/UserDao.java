package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public int insert(UserVo userVo) {
		return sqlSession.insert("user.insert", userVo);

	}

	public UserVo getUser(String id, String password) {
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("password", password);
		userMap.put("id", id);
		
		UserVo vo = sqlSession.selectOne("user.selectUserByIdPw", userMap);
		
		return sqlSession.selectOne("user.selectUserByIdPw", userMap); // selectOne 받는 값이 하나일때 쓴다
	} // selectList는 많은 값을 받을 때 쓴다

	
	
	public UserVo getidcheck(String id) {
		return sqlSession.selectOne("user.getidcheck", id);
	}
}

