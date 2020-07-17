//package com.yugioh;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
//import static org.springframework.restdocs.snippet.Attributes.key;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
//class ApplicationTests {
//
//    private MockMvc mockMvc;
//
//    private String methodName = "{methodName}";
//
//    @BeforeEach
//    public void setUp(WebApplicationContext webApplicationContext,
//                      RestDocumentationContextProvider restDocumentation) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentation))
//                .build();
//    }
//
//    @Test
//    void test1() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/test-one").param("test_one", "1"))
//                .andExpect(status().isOk())
//                .andDo(document("test/" + methodName,
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestParameters(
//                                parameterWithName("test_one").description("测试接口1参数").attributes(key("constraints").value("Must not be null. Must not be empty"))
//                        ),
//                        responseFields(
//                                fieldWithPath("code").description("状态码").type(JsonFieldType.NUMBER).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("msg").description("信息").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("traceId").description("请求唯一id").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data").description("结果").type(JsonFieldType.OBJECT).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.asd").description("参数1").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.qwE").description("参数2").type(JsonFieldType.ARRAY).attributes(key("constraints").value("Must not be null. Must not be empty"))
//                        ))
//                );
//    }
//
//    @Test
//    void test2() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/test-two").param("test_two", "2"))
//                .andExpect(status().isOk())
//                .andDo(document("test/" + methodName,
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestParameters(
//                                parameterWithName("test_two").description("测试接口2参数").attributes(key("constraints").value("Must not be null. Must not be empty"))
//                        ),
//                        responseFields(
//                                fieldWithPath("code").description("状态码").type(JsonFieldType.NUMBER).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("msg").description("信息").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("traceId").description("请求唯一id").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data").description("结果").type(JsonFieldType.OBJECT).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.list").description("对象数组").type(JsonFieldType.ARRAY).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.list[].213").description("参数1").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty"))
//                        ))
//                );
//    }
//
//    @Test
//    void test3() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/test-three"))
//                .andExpect(status().isOk())
//                .andDo(document("test/" + methodName,
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        responseFields(
//                                fieldWithPath("code").description("状态码").type(JsonFieldType.NUMBER).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("msg").description("信息").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("traceId").description("请求唯一id").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data").description("结果").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty"))
//                        ))
//                );
//    }
//
//    @Test
//    void test4() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/test-four").param("time", "2020-06-28 20:15:46"))
//                .andExpect(status().isOk())
//                .andDo(document("test/" + methodName,
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestParameters(
//                                parameterWithName("time").description("测试接口4参数").attributes(key("constraints").value("Must not be null. Must not be empty"))
//                        ),
//                        responseFields(
//                                fieldWithPath("code").description("状态码").type(JsonFieldType.NUMBER).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("msg").description("信息").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("traceId").description("请求唯一id").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data").description("结果").type(JsonFieldType.OBJECT).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.time").description("时间").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.time2").description("时间2").type(JsonFieldType.NULL).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.testMdc").description("参数3").type(JsonFieldType.NULL).attributes(key("constraints").value("Must not be null. Must not be empty"))
//                        ))
//                );
//    }
//
//    @Test
//    void test6() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/test-six").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"time\":\"2020-06-28 20:15:46\",\"time2\":\"2020-06-28\",\"testMdc\":\"dsfsa\"}"))
//                .andExpect(status().isOk())
//                .andDo(document("test/" + methodName,
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                fieldWithPath("time").description("时间1").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("time2").description("时间2").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("testMdc").description("参数3").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty"))
//                        ),
//                        responseFields(
//                                fieldWithPath("code").description("状态码").type(JsonFieldType.NUMBER).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("msg").description("信息").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("traceId").description("请求唯一id").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data").description("结果").type(JsonFieldType.OBJECT).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.time").description("时间").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.time2").description("时间2").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty")),
//                                fieldWithPath("data.testMdc").description("参数3").type(JsonFieldType.STRING).attributes(key("constraints").value("Must not be null. Must not be empty"))
//                        ))
//                );
//    }
//}
