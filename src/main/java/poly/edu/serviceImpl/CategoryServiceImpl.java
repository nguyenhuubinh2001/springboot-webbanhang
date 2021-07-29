package poly.edu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import poly.edu.entity.Category;
import poly.edu.model.CategoryModel;
import poly.edu.model.PageModel;
import poly.edu.repository.CategoryRepository;
import poly.edu.service.CategoryService;
import poly.edu.utils.ObjectMapperUtils;
import poly.edu.utils.PageableUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	public CategoryRepository categoryRepo;
	
	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Override
	public List<CategoryModel> getAllByPage(PageModel pageModel) {
		Pageable pageable = PageableUtils.createPageable(pageModel);
		List<Category> list = categoryRepo.findAll(pageable).getContent();
		List<CategoryModel> listModel = objectMapper.mapAll(list, CategoryModel.class);
		return listModel;
	}

	@Override
	public List<CategoryModel> getAll() {
		List<Category> list = categoryRepo.findAll();
		List<CategoryModel> listModel = objectMapper.mapAll(list, CategoryModel.class);
		return listModel;
	}

	@Override
	public boolean delete(Integer id) {
		Category entity = categoryRepo.findById(id).get();
		if(!entity.getProducts().isEmpty()) {
			return false;
		}
		categoryRepo.deleteById(id);
		return true;
	}

	@Override
	public CategoryModel create(CategoryModel model) {
		Category entity = objectMapper.convertEntityAndDTO(model,Category.class);
		Category entitySave = categoryRepo.save(entity);
		model.setId(entitySave.getId());
		return model;
	}

	@Override
	public CategoryModel update(CategoryModel model) {
		Category entity = objectMapper.convertEntityAndDTO(model,Category.class);
		categoryRepo.save(entity);
		return model;
	}

}
