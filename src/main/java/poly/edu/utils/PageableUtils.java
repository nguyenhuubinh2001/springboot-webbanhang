package poly.edu.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import poly.edu.model.PageModel;


public class PageableUtils {
	public static Pageable createPageable(PageModel pageModel) {
		if(pageModel.getSortBy()==null||pageModel.getSortDirection()==null) {
			pageModel.setSortBy("id");
			pageModel.setSortDirection(Sort.Direction.ASC);
		}
		Sort sort = Sort.by(pageModel.getSortDirection(), pageModel.getSortBy());
		return PageRequest.of(pageModel.getPageNumber(),pageModel.getPageSize(),sort);
	}
}