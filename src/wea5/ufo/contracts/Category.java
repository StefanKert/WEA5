package wea5.ufo.contracts;

import java.util.List;

import com.owlike.genson.GenericType;

public class Category implements Entity<Category>  {
	private String name;
	private int id;
	
	public Category() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public GenericType<Category> getGenericInstance() {
		return new GenericType<Category>(){};
	}

	@Override
	public GenericType<List<Category>> getGenericListInstance() {
		return new GenericType<List<Category>>(){};
	}
}
