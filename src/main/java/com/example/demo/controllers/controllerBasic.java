package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.configuration.Pages;
import com.example.demo.model.Post;

@Controller
@RequestMapping("/home")
public class controllerBasic {
	
	public List<Post> getPosts(){
		ArrayList<Post> post=new ArrayList<>();
		
		post.add(new Post(1,"Descripcion1","http://localhost:8080/img/carrusel1.jpg",new Date(),"Titulo 1"));
		post.add(new Post(2,"Descripcion2","http://localhost:8080/img/carrusel1.jpg",new Date(),"Titulo 2"));
		post.add(new Post(3,"Descripcion3","http://localhost:8080/img/carrusel1.jpg",new Date(),"Titulo 3"));
		post.add(new Post(4,"Descripcion4","http://localhost:8080/img/carrusel1.jpg",new Date(),"Titulo 4"));
		return post;
	}
	
	@GetMapping(path= {"/posts", "/"})
	public String saludar(Model model) {
		model.addAttribute("posts",getPosts());
		return "index";
	}
	
	@GetMapping(path="/public")
	public ModelAndView post() {
		ModelAndView modelAndView = new ModelAndView(Pages.HOME);
		modelAndView.addObject("posts", getPosts());
		return modelAndView;
	}
	
	@GetMapping(path={"/post","/post/{post}"})
	public ModelAndView postId(@RequestParam(defaultValue="1",name="id",required=false)
								@PathVariable(required=true, name="post")int id) {
		ModelAndView modelAndView=new ModelAndView(Pages.POST);
		List<Post> postFiltrado=getPosts().stream()
											.filter((p)->{
												return p.getId()==id;
											}).collect(Collectors.toList());
		modelAndView.addObject("post",postFiltrado.get(0));
		return modelAndView;
	}
}
