package com.knight.service;

import com.knight.dao.InvestDAO;
import com.knight.model.ErrorCode;
import com.knight.model.ProductStatus;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvestService {
    @Autowired
    private InvestDAO investDAO;

    @Autowired
    private SqlSession sqlSession;

    public List<Map> getProduct(String startedAt, String finishedAt) {
        Map<String, Object> params = new HashMap();
        params.put("startedAt", startedAt + " 00:00:00");
        params.put("finishedAt", finishedAt + " 23:59:59");

        List<Map> result = sqlSession.getMapper(InvestDAO.class).selectProducts(params);
        result.stream().forEach(item -> {
            item.put("productStatus", ProductStatus.getFromCode((String) item.get("productStatus")).getName());
        });
        return result;
    }

    public Map<String, Object> createInvest(String userId, Long productId, Long amount) {
        Map<String, Object> result = new HashMap<>();
        result.put("Result", ErrorCode.ERROR.getDescription());

        // 현재 투자 상태 체크
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        Map<String, Object> investStatus = sqlSession.getMapper(InvestDAO.class).selectInvestStatusByProductId(params);
        if(null != investStatus) {
            Long totalInvestingAmount = MapUtils.getLongValue(investStatus, "totalInvestingAmount");
            Long currentInvestingAmount = MapUtils.getLongValue(investStatus, "currentInvestingAmount");
            if(currentInvestingAmount + amount > totalInvestingAmount) {
                result.put("Result", ErrorCode.SOLD_OUT.getDescription());
            } else {
                Map<String, Object> investParams = new HashMap<>();
                investParams.put("userId", userId);
                investParams.put("productId", productId);
                investParams.put("amount", amount);

                if(sqlSession.getMapper(InvestDAO.class).insertInvest(investParams) > 0) {
                    result.put("Result", ErrorCode.OK.getDescription());
                }
            }
        }

        return result;
    }

    public List<Map> getMyInvest(String userId) {
        Map<String, Object> params = new HashMap();
        params.put("userId", userId);

        List<Map> result = sqlSession.getMapper(InvestDAO.class).selectMyInvests(params);

        return result;
    }

}
