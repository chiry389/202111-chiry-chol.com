package com.knight.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface InvestDAO {
    // 주어진 기간내 상품 조회
    public List<Map> selectProducts(Map<String, Object> params);

    // 특정 상품 1건에 대한 투자 현황 조회
    public Map selectInvestStatusByProductId(Map<String, Object> params);

    // 투자
    public int insertInvest(Map<String, Object> params);

    // 나의 투자 상품 조회
    public List<Map> selectMyInvests(Map<String, Object> params);
}
