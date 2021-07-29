package poly.edu.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.edu.entity.Category;
import poly.edu.entity.OrderDetail;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	
	private Integer id;
	
	@NotNull
	@NotBlank
	private String name;
	
	private String image;
	
	@NotNull
	private Integer price;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@NotNull
	private Integer	 available;
	
	
	private CategoryModel category;
	

}
