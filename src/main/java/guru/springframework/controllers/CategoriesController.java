package guru.springframework.controllers;

import java.util.ArrayList;

import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class CategoriesController {

	private final CategoryRepository categoryRepository;

	@GetMapping("/user/{id}/categories")
	public String showById(@PathVariable Long id, Model model) {

		model.addAttribute("userId", id);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("selectedCategories", new ArrayList<Category>());

		return "categories";
	}

}
