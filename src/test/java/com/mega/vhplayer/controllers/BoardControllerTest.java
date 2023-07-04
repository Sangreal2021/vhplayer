package com.mega.vhplayer.controllers;

import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mega.vhplayer.VhplayerApplication;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {VhplayerApplication.class})
@Slf4j
public class BoardControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	// ******************** 테스트 기본 세팅 ********************
	
//	@Test
	public void testList() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/boardlist"))
						.andReturn().getModelAndView().getModelMap().toString());
	}
	
//	@Test
	public void testRegister() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
							.param("title", "cont test")
							.param("content", "cont test 내용")
							.param("user_name", "nada")
							.param("user_auth", "1")).andReturn().getFlashMap().toString();
		log.info(result);
	}
	
//	@Test
	public void testGet() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
					.param("bno", "6"))
				.andReturn().getModelAndView().getModelMap().toString());
	}
	
//	@Test
	public void testModify() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "6")
				.param("title", "modi_title")
				.param("content", "modi Content")
				.param("user_name", "neoda")
				.param("user_auth", "2"))
				.andReturn().getFlashMap().toString();
		log.info("[testModify()] " + result);
	}
	
//	@Test
	public void testRemove() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "6"))
				.andReturn().getFlashMap().toString();
		log.info("[testRemove()] " + result);
	}
}




















