package service;


import com.knight.model.ErrorCode;
import com.knight.service.InvestService;
import org.apache.commons.collections4.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class InvestServiceTest {
    @Mock
    private InvestService investService;

    @Test
    public void getProductTest() {
        List<Map> productList = investService.getProduct("2021-11-01", "2021-12-01");
        assertNotNull(productList);
    }

    @Test
    public void createInvestTest() {
        Map<String, Object> result = investService.createInvest("123", 1L, 100L);
        assertEquals(ErrorCode.OK.getDescription(), MapUtils.getString(result, "Result"));

        Map<String, Object> resultFail = investService.createInvest("123", 1L, 10000000000000000L);
        assertEquals(ErrorCode.SOLD_OUT.getDescription(), MapUtils.getString(resultFail, "Result"));
    }

    @Test
    public void getMyInvestTest() {
        List<Map> myInvestList = investService.getMyInvest("123");
        assertNotNull(myInvestList);
    }
}
