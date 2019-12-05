package com.itachi.invoice.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kong
 */
@RestController
public class TestApi {

    @RequestMapping(value = "/test")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("dateTime", LocalDateTime.now());
        map.put("date", LocalDate.now());
        map.put("time", LocalTime.now());
        return map;
    }

}
