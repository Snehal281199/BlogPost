package com.example.demo.Entity;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="title",length=100,nullable = false)
	private String categoryTitle;
	
	@Column(name="description" ,nullable =false)
	private String categoryDescription;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
	
}
