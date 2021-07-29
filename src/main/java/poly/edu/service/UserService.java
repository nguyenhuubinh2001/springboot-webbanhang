package poly.edu.service;

import java.util.List;

import poly.edu.model.UserModel;
import poly.edu.model.PageModel;

public interface UserService {
	List<UserModel> getAllByPage(PageModel pageModel);
	List<UserModel> getAll();
	boolean delete(Integer id);
	UserModel create(UserModel model);
	UserModel update(UserModel model);
}



