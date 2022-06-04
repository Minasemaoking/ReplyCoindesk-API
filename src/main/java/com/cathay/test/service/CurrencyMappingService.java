package com.cathay.test.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cathay.test.dao.CurrencyMappingDao;
import com.cathay.test.dto.CurrencyMappingDto;
import com.cathay.test.exception.DuplicateIdException;
import com.cathay.test.exception.IdDoesNotExistException;
import com.cathay.test.po.CurrencyMappingEntity;

@Service
public class CurrencyMappingService {

	@Autowired
	private CurrencyMappingDao currencyMappingDao;
	
	public List<CurrencyMappingDto> findAll() {
		List<CurrencyMappingEntity> list = currencyMappingDao.findAll();
		return list.stream().map( c -> new CurrencyMappingDto(c)).collect(Collectors.toList());
	}
	
	public CurrencyMappingDto findById(String engName) {
		CurrencyMappingEntity currencyMappingEntity = currencyMappingDao.findById(engName).orElseThrow(() -> new IdDoesNotExistException(engName + "does not exist"));
		return new CurrencyMappingDto(currencyMappingEntity);
	}
	
	private void idMustNotExist(String engName) {
		
		if(currencyMappingDao.existsById(engName)) {
			throw new DuplicateIdException("Id already exists");
		}
	}
	
	private CurrencyMappingEntity saveByCurrencyMappingDto (CurrencyMappingDto currencyMappingDto) {
		CurrencyMappingEntity currencyMappingEntity  = new CurrencyMappingEntity(currencyMappingDto);
		return currencyMappingDao.save(currencyMappingEntity);
	}
	
	public void create(CurrencyMappingDto currencyMappingDto) {
		idMustNotExist(currencyMappingDto.getEngName());
		saveByCurrencyMappingDto(currencyMappingDto);
	}
	
	private void idHasToExist(String engName) {
		if(!currencyMappingDao.existsById(engName)) {
			throw new IdDoesNotExistException(engName + "does not exist");
		}
	}
	
	public CurrencyMappingDto update(CurrencyMappingDto currencyMappingDto) {	
		idHasToExist(currencyMappingDto.getEngName());
		CurrencyMappingEntity currencyMappingEntity  = saveByCurrencyMappingDto(currencyMappingDto);
		return new CurrencyMappingDto(currencyMappingEntity);
	}
	
	public void delete(String engName) {
		currencyMappingDao.deleteById(engName);
	}
}
