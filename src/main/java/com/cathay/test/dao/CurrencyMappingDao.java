package com.cathay.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cathay.test.po.CurrencyMappingEntity;

@Repository
public interface CurrencyMappingDao extends JpaRepository<CurrencyMappingEntity, String> {

}
