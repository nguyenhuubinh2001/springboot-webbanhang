package poly.edu.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private Integer price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
			name="product_id",
			referencedColumnName = "id" 
	)
	@JsonIgnore
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
			name="user_id",
			referencedColumnName = "id" 
	)
	@JsonIgnore
	private User user;
}	
