package poly.edu.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import poly.edu.entity.Role;
import poly.edu.entity.User;
import poly.edu.entity.User;
import poly.edu.entity.User;
import poly.edu.model.UserModel;
import poly.edu.model.PageModel;
import poly.edu.model.UserModel;
import poly.edu.repository.UserRepository;
import poly.edu.security.jwt.JwtProvider;
import poly.edu.service.UserService;
import poly.edu.utils.ObjectMapperUtils;
import poly.edu.utils.PageableUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ObjectMapperUtils objectMapper;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	HttpServletRequest request;

	@Override
	public List<UserModel> getAllByPage(PageModel pageModel) {
		Pageable pageable = PageableUtils.createPageable(pageModel);
		List<User> list = userRepo.findAll(pageable).getContent();
		List<UserModel> listModel = objectMapper.mapAll(list, UserModel.class);

		for (UserModel userModel : listModel) {
			userModel.setPassword("******");
			userModel.setPasswordConfirm("******");
		}
		return listModel;
	}

	@Override
	public List<UserModel> getAll() {
		List<User> list = userRepo.findAll();
		List<UserModel> listModel = objectMapper.mapAll(list, UserModel.class);
		return listModel;
	}

	@Override
	public UserModel create(UserModel model) {
		User entity = objectMapper.convertEntityAndDTO(model, User.class);
		entity.setPassword(encoder.encode(entity.getPassword()));
		User entitySave = userRepo.save(entity);
		model.setId(entitySave.getId());
		return model;
	}

	@Override
	public UserModel update(UserModel model) {
		User entity = objectMapper.convertEntityAndDTO(model, User.class);
		if (entity.getPassword().equals("******")) {
			entity.setPassword(userRepo.findById(entity.getId()).get().getPassword());
		} else {
			entity.setPassword(encoder.encode(entity.getPassword()));
		}
		userRepo.save(entity);
		return model;
	}

	@Override
	public boolean delete(Integer id) {
		User user = userRepo.findById(id).get();
		for (Role role : user.getRoles()) {
			if (role.getName().name().equals("ROLE_ADMIN")) {
				return false;
			}
			break;
		}
		
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		
		
		if (user.getUsername().equals(username))
			return false;
		userRepo.deleteById(id);
		return true;
	}

}
