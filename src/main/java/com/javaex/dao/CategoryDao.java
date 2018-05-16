package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;


@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getList() {
		return sqlSession.selectList("category.getList");
		
	}
	
	
	
	public void add(CategoryVo categoryVo) {
		sqlSession.insert("category.add", categoryVo);
	}
	
	public int insert2(CategoryVo categoryVo) {
		System.out.println(categoryVo.toString());
		sqlSession.insert("category.insert2", categoryVo);
		System.out.println(categoryVo.toString());
		
		return categoryVo.getCateNo();
	}
	
	public CategoryVo selectCategoryVo(int no) {
		return sqlSession.selectOne("category.selectCategory", no);
	}
	
	
	
	
	
	public void delete(CategoryVo categoryVo) {
		sqlSession.delete("category.delete", categoryVo);
	}
	
	public int delete2(int cateNo) {
		return sqlSession.delete("category.delete2", cateNo);
	}
	
}
