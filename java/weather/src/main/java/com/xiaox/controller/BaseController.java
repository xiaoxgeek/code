package com.xiaox.controller;

import com.xiaox.util.WeatherUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/aqi")
    public String getAQI(@RequestParam("lat") String lat, @RequestParam("lon") String lon, Map<String, Object> map) {
        Map<String, String> param = new HashMap<>();
        param.put("lat", lat);
        param.put("lon", lon);
        String aqi = WeatherUtil.getAQI(param);
        map.put("lat", lat);
        map.put("lon", lon);
        map.put("aqi", aqi);
        return "index";
    }

    @GetMapping(value = "/address")
    public String getAQI(@RequestParam("address") String address, Map<String, Object> map) {
        String lat_lon = WeatherUtil.locationToCoordinate(address);
        String aqi = WeatherUtil.getAQI(lat_lon);
        map.put("address", address);
        map.put("aqi", aqi);
        return "index";
    }

    @GetMapping(value = "/weather")
    public String getWeather(@RequestParam("address") String address, Map<String, Object> map) {
        String aqi = WeatherUtil.getWeather(address);
        map.put("address", address);
        map.put("aqi", aqi);
        return "index";
    }

}
