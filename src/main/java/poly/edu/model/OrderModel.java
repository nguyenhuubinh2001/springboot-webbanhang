package poly.edu.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poly.edu.entity.OrderDetail;
import poly.edu.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
	private Integer id;

	private String address;

	private Date createDate;

	private UserModel user;
	
	private Integer status;
	
	private List<OrderDetailModel> orderDetails;
}
