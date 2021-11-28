package com.knight.controller;

import com.knight.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private InvestService investService;

    /**
     * 1. 전체 투자 상품 조회 API
     *
     * @param startedAt 투자시작일시
     * @param finishedAt 투자종료일시
     * @return
     * {
     *         "productId": 2,
     *         "totalInvestingAmount": 0,
     *         "startedAt": "2021-11-01 00:00:00",
     *         "productStatus": "모집중",
     *         "title": "title2",
     *         "finishedAt": "2021-11-01 00:00:00"
     * },
     */
    @RequestMapping(value="/api/product", method=RequestMethod.GET)
    public List<Map> getProduct(
            @RequestParam(value = "startedAt", required = true) String startedAt,
            @RequestParam(value = "finishedAt", required = true) String finishedAt
    ) {
        List<Map> result = investService.getProduct(startedAt, finishedAt);
        return result;
    }

    /**
     * 2. 투자하기 API
     *
     * @param userId 사용자식별값
     * @param productId 상품ID
     * @param amount 투자금액
     *
     * @return
     *
     */
    @RequestMapping(value="/api/invest", method=RequestMethod.POST)
    public Map<String, Object> createInvest(
            @RequestParam(value="productId", required = true) Long productId,
            @RequestParam(value = "amount", required = true) Long amount
    ) {
        String userId = request.getHeader("X-USER-ID");
        return investService.createInvest(userId, productId, amount);
    }

    /**
     * 3. 나의 투자상품 조회 API
     *
     * @param userId 사용자식별값
     *
     * @return
     * productId 상품ID
     * title 상품 제목
     * totalInvestingAmount 총모집금액
     * myInvestingAmount 나의투자금액
     * investedAt 투자일시
     */
    @RequestMapping(value="/api/myinvest", method=RequestMethod.GET)
    public List<Map> getMyInvest() {
        String userId = request.getHeader("X-USER-ID");
        return investService.getMyInvest(userId);
    }
}
