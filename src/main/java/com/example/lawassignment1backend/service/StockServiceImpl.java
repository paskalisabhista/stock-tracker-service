package com.example.lawassignment1backend.service;

import com.example.lawassignment1backend.model.Stock;
import com.example.lawassignment1backend.repository.StockRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    private final String companyUrl = "https://api.goapi.id/v1/stock/idx/companies";
    private final String priceUrl = "https://api.goapi.id/v1/stock/idx/prices";

    @Value("${app.api-key}")
    private String key;

    @Autowired
    StockRepository stockRepository;

    @Override
    public void updateStocks() {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(companyUrl)
                .queryParam("api_key", key);

        URI uri = builder.build().toUri();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        var parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(response.getBody());
        } catch (ParseException e) {
            return;
        }

        var jsonObject = (JSONObject) obj;

        var statusJsonObject = (List)((JSONObject) jsonObject.get("data")).get("results");

        for (Object tempObj: statusJsonObject) {
            String tempSymbol = (String)((JSONObject)tempObj).get("ticker");
            String tempCompanyName = (String)((JSONObject)tempObj).get("name");
            Stock stock = new Stock(tempSymbol, tempCompanyName);
            stockRepository.add(stock);
        }
    }

    @Override
    public Long updateClosedPrice(String symbol) {
        Date today = new Date();
        Stock stock = stockRepository.findBySymbol(symbol);

        if (stock.getLastUpdate().equals(today)){
            return stock.getClosedPrice();
        }

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(priceUrl)
                .queryParam("api_key", key)    // token
                .queryParam("symbols", symbol);

        URI uri = builder.build().toUri();

        ResponseEntity<String> response = null;

        try{
            response = restTemplate.getForEntity(uri, String.class);
        } catch (RestClientException e) {   // this exception will catch an error from api calls
            e.printStackTrace();
            return stock.getClosedPrice();
        }

        var parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(response.getBody());
        } catch (ParseException e) {
            return stock.getClosedPrice();
        }

        var jsonObject = (JSONObject) obj;

        var statusJsonObject = (List)((JSONObject) jsonObject.get("data")).get("results");

        Long closedPrice = 0L;
        for (Object tempObj: statusJsonObject) {
            closedPrice = Long.valueOf((String) ((JSONObject)tempObj).get("close"));
        }
        return closedPrice;
    }
}
