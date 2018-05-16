package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;

	public int join(UserVo userVo) {
		int x = userDao.insert(userVo);
		blogDao.makeblog(userVo);
		
		return x;
	}

	public UserVo loginform(String id, String password) {
	
		
		return userDao.getUser(id, password);
	}

    public boolean idcheck(String id) {
	if(null == userDao.getidcheck(id)) {         /* email 을 파라미터로 이용해서 userDao에 있는 getidcheck 메소드를 호출한다 */
	
		return false;
	} else {return true;}
}
}