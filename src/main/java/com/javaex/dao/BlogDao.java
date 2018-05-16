package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.javaex.vo.BlogVo;

import com.javaex.vo.UserVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo blog(String id) {
		
		return sqlSession.selectOne("blog.bloginfo", id);
	}
	
    public void makeblog(UserVo userVo) {
		
		String id = userVo.getId();
		String blogTitle =  userVo.getUserName() + "님의 블로그 입니다."; 
		String logoFile = "${pageContext.request.contextPath}/assets/images/spring-logo.jpg";

	    Map<String, Object> makeblog = new HashMap<String, Object>();
	    makeblog.put("id", id);
	    makeblog.put("blogTitle", blogTitle);
	    makeblog.put("logoFile", logoFile);
	    
	     sqlSession.insert("blog.makeblog", makeblog);
	    	
    }
    
    public void modify(BlogVo blogVo) {
		sqlSession.update("blog.modify", blogVo);
	
    }
}



