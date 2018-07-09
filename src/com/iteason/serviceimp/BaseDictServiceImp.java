package com.iteason.serviceimp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iteason.dao.BaseDictDao;
import com.iteason.domain.BaseDict;
import com.iteason.service.BaseDictService;
@Service("baseDictService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class BaseDictServiceImp implements BaseDictService {
	@Resource(name="baseDictDao")
	private BaseDictDao bdd;
		
	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		
		return bdd.getListByTypeCode(dict_type_code);
	}


	public void setBdd(BaseDictDao bdd) {
		this.bdd = bdd;
	}

}
