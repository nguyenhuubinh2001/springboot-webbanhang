package poly.edu.service;

import java.util.List;

import poly.edu.model.CategoryModel;
import poly.edu.model.PageModel;

public interface CategoryService {
	List<CategoryModel> getAllByPage(PageModel pageModel);
	List<CategoryModel> getAll();
	boolean delete(Integer id);
	CategoryModel create(CategoryModel model);
	CategoryModel update(CategoryModel model);
}
