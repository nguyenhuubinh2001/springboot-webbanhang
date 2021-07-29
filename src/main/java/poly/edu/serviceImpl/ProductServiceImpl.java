package poly.edu.serviceImpl;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.entity.Category;
import poly.edu.entity.Product;
import poly.edu.model.CategoryModel;
import poly.edu.model.PageModel;
import poly.edu.model.ProductModel;
import poly.edu.repository.CategoryRepository;
import poly.edu.repository.ProductRepository;
import poly.edu.service.ProductService;
import poly.edu.utils.ObjectMapperUtils;
import poly.edu.utils.PageableUtils;
import poly.edu.utils.UploadFileUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ObjectMapperUtils objectMapper;

	@Autowired
	CategoryRepository cateRepo;

	@Autowired
	Category category;

	@Autowired
	private ProductRepository repository;

	@Autowired
	private UploadFileUtils uploadFile;
	
	@Override
	public List<ProductModel> getAllByPage(PageModel pageModel, String name) {
		Pageable pageable = PageableUtils.createPageable(pageModel);
		List<Product> list = repository.findByNameContaining(name, pageable).getContent();
		List<ProductModel> listModel = objectMapper.mapAll(list, ProductModel.class);
		return listModel;
	}

	@Override
	public boolean delete(Integer id) {
		Product entity = repository.findById(id).get();
		if (!entity.getOrderDetails().isEmpty()) {
			return false;
		}
		repository.deleteById(id);
		return true;

	}

	@Override
	public ProductModel create(ProductModel model, Integer categoryId, Optional<MultipartFile> file) {
		category.setId(categoryId);
		Product entity = objectMapper.convertEntityAndDTO(model, Product.class);
		if (file.isPresent()) {
			String uuid = UUID.randomUUID().toString();
			String fileName = uuid+"_"+file.get().getOriginalFilename();
			uploadFile.handleUploadFile(file.get(),fileName);
			entity.setImage(fileName);
		}else {
			entity.setImage("gai.jpg");
		}
		entity.setCategory(category);
		Product entitySave = repository.save(entity);
		model.setId(entitySave.getId());
		return model;
	}
	
	@Override
	public ProductModel update(ProductModel model, Integer categoryId, Optional<MultipartFile> file) {
		category.setId(categoryId);
		Product entity = objectMapper.convertEntityAndDTO(model, Product.class);
		if (file.isPresent()) {
			String uuid = UUID.randomUUID().toString();
			String fileName = uuid+"_"+file.get().getOriginalFilename();
			uploadFile.handleUploadFile(file.get(),fileName);
			entity.setImage(fileName);
		}else {
			System.out.println("Không có file");
		}
		entity.setCategory(category);
		repository.save(entity);
		return model;
	}

	@Override
	public List<ProductModel> getAllByCategoryAndName(PageModel pageModel, Integer categoryId,String name) {
		Pageable pageable = PageableUtils.createPageable(pageModel);
		Category category = cateRepo.findById(categoryId).get();
		List<Product> list = repository.findByCategoryAndNameContaining(category,name, pageable).getContent();
		List<ProductModel> listModel = objectMapper.mapAll(list, ProductModel.class);
		return listModel;
	}

	@Override
	public Integer getCountByCategoryAndName(Integer categoryId, String name) {
		Category category = cateRepo.findById(categoryId).get();
		List<Product> list = repository.findByCategoryAndNameContaining(category, name);
		Integer count = list.size();
		return count;
	}

	@Override
	public Integer getCountByName(String name) {
		List<Product> list = repository.findByNameContaining(name);
		return list.size();
	}

	@Override
	public List<ProductModel> getAll() {
		List<Product> list = repository.findAll();
		List<ProductModel> listModel = objectMapper.mapAll(list,ProductModel.class);
		return listModel;
	}

	@Override
	public List<ProductModel> findByCategoryId(Integer cid) {
		List<Product> list = repository.findByCategoryId(cid);
		List<ProductModel> listModel = objectMapper.mapAll(list,ProductModel.class);
		return listModel;
	}

}
