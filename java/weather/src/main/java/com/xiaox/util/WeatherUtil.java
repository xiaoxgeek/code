package com.xiaox.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

public class WeatherUtil {
    public static void main(String[] args) {
        // http://www.weather.com.cn/data/cityinfo/101010100.html
        String url = "http://www.weather.com.cn/data/cityinfo/101010100.html";
        String url2 = "http://www.weather.com.cn/data/sk/101010100.html";
        String url3 = "http://m.weather.com.cn/data/101010100.html";
        String url4 = "http://e.healthweather.cn/air/?lat=31.65381&lon=120.75224";
        String content = HttpUtil.doGet(url4);
//        Map<String, String> param = new HashMap<>();
//        param.put("lat", "31.65381");
//        param.put("lon", "120.75224");
//        System.out.println("空气质量是："+getAQI(param));
        System.out.println(getWeather("北京"));

    }

    // 查询天气信息
    public static String getWeather(Map<String, String> param) {
        // http://e.weather.com.cn/d/town/index?lat=31.65381&lon=120.75224
        String url = String.format("http://e.weather.com.cn/d/town/index?lat=%s&lon=%s", param.get("lat"), param.get("lon"));
        String content = HttpUtil.doGet(url);
        Document doc = Jsoup.parse(content);

        StringBuffer sb = new StringBuffer("");
        sb.append("温度 " + doc.select("body > div.main > div.bgs > div.n_wd > h1").select("span").text() + "°C");
        sb.append(", 天气 " + doc.select("body > div.main > div.bgs > div.n_wd > h1").select("em").text());
        sb.append(", " + doc.select("body > div.main > div.bgs > div.n_wd > h2 > span.flfx.iconli").text());
        sb.append(", " + doc.select("body > div.main > div.bgs > div.n_wd > h2 > span.xdsd.iconli").text());
        sb.append(", 空气质量" + getAQI(param));
        return sb.toString();
    }
    public static String getWeather(String address) {
        String str = locationToCoordinate(address);
        String[] strs = str.split(",");
        Map<String, String> param = new HashMap<>();
        param.put("lat", strs[0]);
        param.put("lon", strs[1]);
        return getWeather(param);
    }

    // 根据经纬度查询空气质量
    public static String getAQI(Map<String, String> map) {
        String url = String.format("http://e.healthweather.cn/air/?lat=%s&lon=%s", map.get("lat"), map.get("lon"));
        String content = HttpUtil.doGet(url);
        Document doc = Jsoup.parse(content);
        String result = doc.select(".aqi-num").text();
        return result;
    }

    // 根据经纬度查询空气质量
    public static String getAQI(String str) {
        Map<String, String> param = new HashMap<>();
        String[] strs = str.split(",");
        param.put("lat", strs[0]);
        param.put("lon", strs[1]);
        return getAQI(param);
    }

    public static String locationToCoordinate(String address, String ak) {
        String url = String.format("http://api.map.baidu.com/geocoder/v2/?address=%s&ak=%s&callback=showLocation", address, ak);
        String content = HttpUtil.doGet(url);
        Document doc = Jsoup.parse(content);
        String lat = doc.select("lat").text();
        String lng = doc.select("lng").text();
        return lat+","+lng;
    }

    public static String locationToCoordinate(String address) {
        return locationToCoordinate(address, "63jVhGBejmt47zgMYtt63QoVIXFDGIOZ");
    }
}
