package com.iteason.dao;

import java.util.List;

import com.iteason.domain.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict>{
	public List<BaseDict> getListByTypeCode(String dict_typeCode);
}
