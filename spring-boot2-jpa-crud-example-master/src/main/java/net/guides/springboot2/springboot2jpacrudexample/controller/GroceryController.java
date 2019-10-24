package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Grocery;
import net.guides.springboot2.springboot2jpacrudexample.repository.GroceryRepository;

@RestController
@RequestMapping("/api/v1")
public class GroceryController {
	@Autowired
	private GroceryRepository groceryRepository;

	@GetMapping("/groceries")
	public List<Grocery> getAllGroceries() {
		return groceryRepository.findAll();
	}

	@GetMapping("/groceries/{itemcode}")
	public ResponseEntity<Grocery> getGroceryByItemCode(@PathVariable(value = "itemcode") Long groceryId)
			throws ResourceNotFoundException {
		Grocery grocery = groceryRepository.findById(groceryId)
				.orElseThrow(() -> new ResourceNotFoundException("Grocery not found for this item code :: " + groceryId));
		return ResponseEntity.ok().body(grocery);
	}

	@PostMapping("/groceries")
	public Grocery createGrocery(@Valid @RequestBody Grocery grocery) {
		return groceryRepository.save(grocery);
	}

	@PutMapping("/groceries/{itemcode}")
	public ResponseEntity<Grocery> updateGrocery(@PathVariable(value = "itemcode") Long groceryId,
			@Valid @RequestBody Grocery groceryDetails) throws ResourceNotFoundException {
		Grocery grocery = groceryRepository.findById(groceryId)
				.orElseThrow(() -> new ResourceNotFoundException("Grocery not found for this item code :: " + groceryId));

		grocery.setItemName(groceryDetails.getItemName());
		grocery.setAmount(groceryDetails.getAmount());
		final Grocery updatedGrocery = groceryRepository.save(grocery);
		return ResponseEntity.ok(updatedGrocery);
	}

	@DeleteMapping("/groceries/{itemcode}")
	public Map<String, Boolean> deleteGrocery(@PathVariable(value = "itemcode") Long groceryId)
			throws ResourceNotFoundException {
		Grocery grocery = groceryRepository.findById(groceryId)
				.orElseThrow(() -> new ResourceNotFoundException("Grocery not found for this item code :: " + groceryId));

		groceryRepository.delete(grocery);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
