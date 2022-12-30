package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.PayRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PayServiceTest {

    @Autowired
    private PayService payService;
    @Test
    void paySuccessFul() throws JSONException {
            String[] time = {"2021-05-20 19:56:39"};
            String[] isbn = {"7892596523-001"};
            String userName = "19302010084";
            PayRequest request = new PayRequest(time, isbn, userName);
            String result = payService.pay(request);
            assertEquals(result, "Pay Successful!");
    }

    @Test
    void httpRestClient() throws JSONException {
            String url = "http://47.103.205.96:8080/api/payment";

            HttpMethod method = HttpMethod.POST;
            JSONObject body = new JSONObject();
            body.put("invoke_id", "se2021_19");
            body.put("uid", "19302010084");
            body.put("amount", 25);

            String result = payService.HttpRestClient(url, method, body);
            assertTrue(result.equals("{\"msg\":\"success\"}\n")||result.equals("409 CONFLICT: [{\"msg\":\"failed, the balance is not enough\"}\n" + "]"));
    }

}
