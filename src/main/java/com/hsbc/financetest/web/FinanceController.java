package com.hsbc.financetest.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hsbc.financetest.model.AccountInfoResponse;
import com.hsbc.financetest.model.TransferMoneyRequest;
import com.hsbc.financetest.model.TransferMoneyResponse;
import com.hsbc.financetest.service.FinaceServiceImpl;

@RequestMapping(value = { "/api" })
@RestController
public class FinanceController {

	@Value("${server_url}")
	String server_url;

	@Autowired
	RestTemplate restTemplate;

	@Resource
	FinaceServiceImpl finaceService;

	@RequestMapping(method = RequestMethod.GET, value = "/account-balance/{accountNo}")
	public ResponseEntity<AccountInfoResponse> getAccountInfo(@PathVariable String accountNo) {

		AccountInfoResponse response = finaceService.getAccountInfo(accountNo);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transfer-money")
	public ResponseEntity<TransferMoneyResponse> transferMoney(@Valid @RequestBody TransferMoneyRequest req) {

		TransferMoneyResponse response = finaceService.transferMoney(req.getAccountNoFrom(), req.getAccountNoTo(),
				req.getAmount());

		// call /account-balance if transaction is completed
		if (response.getStatus().equals("Success")) {
			ResponseEntity<AccountInfoResponse> accountRes = restTemplate.getForEntity(
					server_url + "/api/account-balance/{accountNo}", AccountInfoResponse.class, req.getAccountNoFrom());
			AccountInfoResponse accountInfo = accountRes.getBody();

			response.setAccountNo(accountInfo.getAccountNo());
			response.setAccountBalance(accountInfo.getAccountBalance());
			response.setCurrency(accountInfo.getCurrency());
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
