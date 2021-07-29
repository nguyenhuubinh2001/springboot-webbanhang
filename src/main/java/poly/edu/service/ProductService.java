package poly.edu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import poly.edu.model.CategoryModel;
import poly.edu.model.PageModel;
import poly.edu.model.ProductModel;

public interface ProductService {
	List<ProductModel> getAll();
	List<ProductModel> getAllByPage(PageModel pageModel, String name);
	List<ProductModel> getAllByCategoryAndName(PageModel pageModel, Integer categoryId, String name);
	boolean delete(Integer id);
	ProductModel create(ProductModel model, Integer categoryId, Optional<MultipartFile> file);
	ProductModel update(ProductModel model, Integer categoryId, Optional<MultipartFile> file);
	Integer getCountByCategoryAndName(Integer categoryId,String name);
	Integer getCountByName(String name);
	List<ProductModel> findByCategoryId(Integer cid);
}
