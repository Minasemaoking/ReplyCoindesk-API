package com.cathay.test.service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cathay.test.dao.CurrencyMappingDao;
import com.cathay.test.dto.CoindeskDto;
import com.cathay.test.dto.OriginCoindeskDto;
import com.cathay.test.po.CurrencyMappingEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoindeskService {

	@Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CurrencyMappingDao currencyMappingDao;
    @Value("${coindesk.url}")
    private String coindeskUrl;
    
    @PostConstruct
    private void init() {
        //取得幣別對應的中文名稱
        Map<String, String> currencyMap = currencyMappingDao.findAll()
                .stream()
                .collect(Collectors.toMap(CurrencyMappingEntity::getEngName, CurrencyMappingEntity::getChName));
        //幣別資料轉換規則設定
        modelMapper.createTypeMap(OriginCoindeskDto.Bpi.class, CoindeskDto.Currency.class)
                .addMappings(mapper -> {
                    mapper.using(ctx -> currencyMap.get(ctx.getSource().toString()))
                            .map(OriginCoindeskDto.Bpi::getCode, CoindeskDto.Currency::setChName);
                    mapper.map(OriginCoindeskDto.Bpi::getRateFloat, CoindeskDto.Currency::setRate);
                });
        //外層資料轉換規則設定
        modelMapper.createTypeMap(OriginCoindeskDto.class, CoindeskDto.class)
                .addMappings(mapper -> {
                    mapper.using(ctx -> OffsetDateTime.parse(ctx.getSource().toString()).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime())
                            .map(source -> source.getTime().getUpdatedISO(), CoindeskDto::setTime);
                    mapper.map(OriginCoindeskDto::getBpi, CoindeskDto::setCurrencies);
                });
    }
    
    public CoindeskDto query() throws JsonMappingException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        //因為Content-Type是application/javascript，無法透過RestTemplate直接幫你轉成POJO，所以先用字串接
        String content = restTemplate.getForObject(coindeskUrl, String.class);
        //透過ObjectMapper把json字串轉成POJO
        OriginCoindeskDto originCoindeskDto = objectMapper.readValue(content, OriginCoindeskDto.class);
        //資料轉換
        return modelMapper.map(originCoindeskDto, CoindeskDto.class);
    }
}
