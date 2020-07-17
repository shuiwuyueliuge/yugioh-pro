//package com.yugioh;
//
//import cn.minazukiruka.springboot.web.template.core.annotation.ArgumentMark;
//import cn.minazukiruka.springboot.web.template.core.annotation.ResultWrapper;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@RestController
//@ResultWrapper
//public class TestWeb {
//
//    @Autowired
//    private TestService testService;
//
//    @RequestMapping("/test-one")
//    public Map<String, Object> test1(@RequestParam("test_one") String testOne) {
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("asd", testService.testService());
//        map.put("qwE", Stream.of(new Integer[] { 1, 2}).collect(Collectors.toList()));
//        return map;
//    }
//
//    @RequestMapping("/test-two")
//    public Object test2(@RequestParam("test_two") String testTwo) {
//        Map<String, String> map = Maps.newHashMap();
//        map.put("213", "asda");
//        List<Map<String, String>> list = Lists.newArrayList();
//        list.add(map);
//        Map<String, Object> map2 = Maps.newHashMap();
//        map2.put("list", list);
//        return map2;
//    }
//
//    @RequestMapping(value = "/test-three")
//    public String test3() {
//        return testService.testService();
//    }
//
//    @RequestMapping(value = "/test-four")
//    public TestVO test4(LocalDateTime time) {
//        return new TestVO(time);
//    }
//
//    @RequestMapping(value = "/test5")
//    public TestVO test5(@ArgumentMark TestVO vo) {
//        return vo;
//    }
//
//    @RequestMapping(value = "/test-six")
//    public TestVO test6(@RequestBody @ArgumentMark TestVO vo) {
//        return vo;
//    }
//}
