package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Controller

public class ApiCategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/{id}/admin/category", method = RequestMethod.GET)
	public String blogmo(@PathVariable("id") String id, Model model) {
		BlogVo blogvo = categoryService.cat(id);
		model.addAttribute("blogvo", blogvo);
		return "blog/admin/blog-admin-cate";

	}

	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/list", method = RequestMethod.GET)
	public List<CategoryVo> list() {
		System.out.println("ajax-list : category");
		List<CategoryVo> list = categoryService.getList();
		System.out.println(list.toString()); // toString은 안에 있는 정보를 toString으로 출력하겠다!

		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/admin/addcategory", method = RequestMethod.POST)
	public CategoryVo add(@ModelAttribute CategoryVo categoryVo) {
		System.out.println("add");
		System.out.println(categoryVo.toString());
		/* return 0; */

		return categoryService.write(categoryVo);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/admin/delete2", method = RequestMethod.POST)
	public int delete(@RequestParam("cateNo") int cateNo) { 
    int delno = categoryService.delete2(cateNo);
    if(delno !=0) {
    	return cateNo;
    
    } else { 
    	return 0;
    }

}

}
