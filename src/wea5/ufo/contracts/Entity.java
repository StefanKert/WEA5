package wea5.ufo.contracts;

import java.util.List;

import com.owlike.genson.GenericType;

public interface Entity<T> {
	int getId();
	
	GenericType<T> getGenericInstance();
	GenericType<List<T>> getGenericListInstance();
}
