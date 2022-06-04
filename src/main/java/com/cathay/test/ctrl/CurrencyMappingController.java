package com.cathay.test.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.test.dto.CurrencyMappingDto;
import com.cathay.test.service.CurrencyMappingService;

@RestController
@RequestMapping("currencyMapping")
public class CurrencyMappingController {

	@Autowired
	CurrencyMappingService currencyMappingService;
	
	@RequestMapping(value = "/findAll", method = { RequestMethod.GET})
	public List<CurrencyMappingDto> findAll() {
		
		return currencyMappingService.findAll();
	}

	@RequestMapping(value = "/findById", method = { RequestMethod.GET})
	public CurrencyMappingDto findById(@RequestParam String engName) {
		
		return currencyMappingService.findById(engName);
	}

	@RequestMapping(value = "/create", method = { RequestMethod.POST})
	public void create(@RequestBody CurrencyMappingDto currencyMappingDto) {
		
		currencyMappingService.create(currencyMappingDto);
	}

	@RequestMapping(value = "/update", method = { RequestMethod.PUT})
	public CurrencyMappingDto update(@RequestBody CurrencyMappingDto currencyMappingDto) {
		
		return currencyMappingService.update(currencyMappingDto);
	}
	
	@RequestMapping(value = "/delete/{engName}", method = { RequestMethod.DELETE})
	public void delete(@PathVariable String engName) {
		
		currencyMappingService.delete(engName);
	}
}
