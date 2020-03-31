package com.hsbc.financetest.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ControllerTest {

	@Autowired
	protected WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testWithValidInputAccountBalance() throws Exception {
		JSONObject mockResult = new JSONObject();
		mockResult.put("status", "Success");
		mockResult.put("accountNo", "123456789");
		mockResult.put("accountBalance", 3355.0);
		mockResult.put("currency", "HKD");

		String accountNo = "123456789";

		MvcResult res = this.mockMvc.perform(get("/api/account-balance/" + accountNo)).andExpect(status().isOk())
				.andReturn();

		JSONAssert.assertEquals(mockResult.toString(), res.getResponse().getContentAsString(), true);
	}

	@Test
	public void testWithInValidInputAccountBalance() throws Exception {
		JSONObject mockResult = new JSONObject();
		mockResult.put("status", "Error");
		mockResult.put("message", "Invalid Account");

		String accountNo = "12345678";

		MvcResult res = this.mockMvc.perform(get("/api/account-balance/" + accountNo)).andExpect(status().isOk())
				.andReturn();

		JSONAssert.assertEquals(mockResult.toString(), res.getResponse().getContentAsString(), true);
	}

	@Test
	@Ignore
	public void testWithValidInputTransferMoney() throws Exception {
		JSONObject params = new JSONObject();
		params.put("accountNoFrom", "123456789");
		params.put("accountNoTo", "987654321");
		params.put("amount", 1000.0);

		MvcResult res = this.mockMvc
				.perform(
						post("/api/transfer-money/").contentType(MediaType.APPLICATION_JSON).content(params.toString()))
				.andExpect(status().isOk()).andReturn();

		Assert.assertTrue(res.getResponse().getContentAsString().indexOf("transactionID") > 0);
	}

	@Test
	@Ignore
	public void testWithInValidInputTransferMoney() throws Exception {
		JSONObject params = new JSONObject();
		params.put("accountNoFrom", "12345678");
		params.put("accountNoTo", "987654321");
		params.put("amount", 1000.0);

		JSONObject mockResult = new JSONObject();
		mockResult.put("status", "Error");
		mockResult.put("message", "Invalid Account");

		MvcResult res = this.mockMvc
				.perform(
						post("/api/transfer-money/").contentType(MediaType.APPLICATION_JSON).content(params.toString()))
				.andExpect(status().isOk()).andReturn();

		JSONAssert.assertEquals(mockResult.toString(), res.getResponse().getContentAsString(), true);
	}
}
