package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private BlogDao blogdao;

	public List<CategoryVo> getList() {
		return categoryDao.getList();
	}

	public BlogVo cat(String id) {
		return blogdao.blog(id);
	}

	public void add(CategoryVo categoryVo) {
		categoryDao.add(categoryVo);
	}

	public CategoryVo write(CategoryVo categoryVo) {
		int no = categoryDao.insert2(categoryVo);

		return categoryDao.selectCategoryVo(no);
	}
	
	
	
	
	public void delete(CategoryVo categoryVo) {
		categoryDao.delete(categoryVo);
	}
	
	public int delete2(int cateNo) {	
		
		
		return categoryDao.delete2(cateNo);
	}
	
	
	
}