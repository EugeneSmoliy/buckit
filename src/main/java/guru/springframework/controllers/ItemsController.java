package guru.springframework.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import guru.springframework.domain.Category;
import guru.springframework.domain.Item;
import guru.springframework.domain.ItemForView;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.ItemRepository;
import guru.springframework.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ItemsController {

	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final ItemRepository itemRepository;

	@GetMapping("user/{id}/items")
	public String showById(@PathVariable Long id,
			@RequestParam("idChecked") List<String> categoryIds, Model model) {

		List<Category> categories = StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
				.filter(category -> categoryIds.contains(category.getId().toString()))
				.collect(Collectors.toList());
		Category build = Category.builder()
				.id(99L)
				.description("I'm feeling lucky").build();
		categories.add(build);
		model.addAttribute("categories", categories);
		List<Item> allItems = StreamSupport.stream(itemRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		List<ItemForView> items = allItems.stream()
				.filter(item -> categories.contains(item.getCategory()))
				.map(item -> new ItemForView(item.getId(), item.getName(), item.getName(), item.getCategory().getId(), item.getIcon()))
				.collect(Collectors.toList());
		Item item;
		ItemForView itemForView = new ItemForView(29L, "Pet care",
				"Hey you, office plankton! How about giving some pet care?", 99L, "/images/everest.png");
////		Pick random
//		do {
//			item = allItems.get(new Random().nextInt(allItems.size()));
//			itemForView = new ItemForView(item.getName(), item.getCategory().getId(),
//					item.getIcon());
//		} while (items.contains(itemForView));
		itemForView.setCategory(99L);
		items.add(itemForView);
		model.addAttribute("products", items);

		return "items";
	}

	@GetMapping("/user/{userId}/item/{itemId}")
	public String showById(@PathVariable Long userId, @PathVariable Long itemId, Model model) {

		Item item = itemRepository.findById(itemId).get();
		model.addAttribute("item", new ItemForView(29L, item.getName(),
				item.getName(), 99L, "/images/everest.png"));

		return "item";
	}

}
