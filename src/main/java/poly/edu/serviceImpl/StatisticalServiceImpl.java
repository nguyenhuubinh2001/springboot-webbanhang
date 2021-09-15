package poly.edu.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.edu.repository.OrderRepository;
import poly.edu.service.StatisticalService;
@Service
public class StatisticalServiceImpl implements StatisticalService {
	
	@Autowired
	OrderRepository orderRepo;
	
	
	@Override
	public Object getSales(Integer year) {
		return orderRepo.getSaleByYear(year);
	}
	
}
