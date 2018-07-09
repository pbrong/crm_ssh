package com.iteason.service;

import java.util.List;

import com.iteason.domain.BaseDict;

public interface BaseDictService {

	public List<BaseDict> getListByTypeCode(String dict_typeCode);
	
}
