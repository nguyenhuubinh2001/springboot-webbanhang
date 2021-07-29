package poly.edu.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poly.edu.entity.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
	private Integer id;
	private String name;
	private String description;
}