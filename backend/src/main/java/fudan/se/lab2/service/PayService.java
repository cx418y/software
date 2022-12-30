package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.PayRequest;
import fudan.se.lab2.domain.*;

import fudan.se.lab2.exception.PayFailedException;
import fudan.se.lab2.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PayService {
    @Autowired
    private FineRepository fineRepository;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    public PayService(){}

    public String pay(PayRequest request) throws JSONException {
        String username = request.getUserName();
        String[] isbn = request.getIsbn();
        String[] time = request.getTime();
        int len = time.length;
        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        float amount = 0;

        for(int i = 0; i < len; i++){
            Fine f = fineRepository.findByUsernameAndIsbnAndStartTime(username, isbn[i], time[i]);
            amount += (float)f.getPrice();
        }

        String url = "http://47.103.205.96:8080/api/payment";

        HttpMethod method =HttpMethod.POST;
        JSONObject body = new JSONObject();
        body.put( "invoke_id","se2021_19");
        body.put("uid", username);
        body.put("amount", amount);
        //发送http请求并返回结果
        String result = HttpRestClient(url,method,body);

        if(result.equals("{\"msg\":\"success\"}\n")||result.equals("409 CONFLICT: [{\"msg\":\"failed, the balance is not enough\"}\n]")) {
            for(int i = 0; i < len; i++){
                Fine f = fineRepository.findByUsernameAndIsbnAndStartTime(username, isbn[i], time[i]);
               // amount += (float)f.getPrice();
                 f.setPaid(1);
                 f.setPayTime(sdf.format(d));
                 fineRepository.save(f);
            }
            return "Pay Successful!";
        }else {
            throw new PayFailedException(result);
        }
    }

    public static String HttpRestClient(String url, HttpMethod method, JSONObject json){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10*1000);
        requestFactory.setReadTimeout(10*1000);
        RestTemplate client = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(json.toString(), headers);
        //  执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }





}
