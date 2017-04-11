package edu.cesusc.search;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

	private static ModelMapper modelMapper = new ModelMapper();
	
	public static Object toDTO(Object object, Class dtoClass){
		return modelMapper.map(object, dtoClass);
	}
	
	public static Object fromDTO(Object object, Class modelClass){
		return modelMapper.map(object, modelClass);
	}
	
	
}
