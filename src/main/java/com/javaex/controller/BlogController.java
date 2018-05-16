package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;


@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String blog(@PathVariable("id") String id, Model model) { // PathVariable 위에 value에있는 id룰 내려받아서 쓰기 위해서 쓴다
		BlogVo blogvo = blogService.blog(id);
		model.addAttribute("blogvo", blogvo);
		return "blog/blog-main";

	}

	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.GET)
	public String blogmo(@PathVariable("id") String id, Model model) {

		BlogVo blogVo = blogService.blog(id);
		model.addAttribute("blogvo", blogVo);
		return "blog/admin/blog-admin-basic";

	}

	@RequestMapping(value = "/{id}/admin/write", method = RequestMethod.GET)
	public String blogwr(@PathVariable("id") String id, Model model) {

		BlogVo blogVo = blogService.blog(id);
		model.addAttribute("blogvo", blogVo);
		return "blog/admin/blog-admin-write";
	}

	@RequestMapping(value = "/{id}/admin/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model, @ModelAttribute BlogVo blogVo) {
		
		blogService.modify(blogVo, file);
		model.addAttribute("blogVo", file);

		return "redirect:/"+blogVo.getId()+"/admin/basic";

	}
}
