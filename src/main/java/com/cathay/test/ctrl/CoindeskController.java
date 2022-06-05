package com.cathay.test.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.test.dto.CoindeskDto;
import com.cathay.test.service.CoindeskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RequestMapping("coindesk")
@RestController
public class CoindeskController {

	@Autowired
	private CoindeskService coindeskService;
	
	@RequestMapping(method = { RequestMethod.GET})
    public CoindeskDto query() throws JsonMappingException, JsonProcessingException {
        CoindeskDto coindeskDto = coindeskService.query();
        return coindeskDto;
    }
}
