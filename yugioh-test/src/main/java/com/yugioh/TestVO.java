//package com.yugioh;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//public class TestVO {
//
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private LocalDateTime time;
//
//    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
//    private LocalDate time2;
//
//    private String testMdc;
//
//    public TestVO(LocalDateTime time) {
//        this.time = time;
//    }
//}