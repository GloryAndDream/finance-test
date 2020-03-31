package com.hsbc.financetest.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.dizitart.no2.Document;
import org.springframework.stereotype.Service;

import com.hsbc.financetest.dao.AccountDAO;
import com.hsbc.financetest.model.AccountInfoResponse;
import com.hsbc.financetest.model.TransferMoneyResponse;

@Service
public class FinaceServiceImpl {

	@Resource
	AccountDAO accountDAO;

	@PostConstruct
	public void initData() {
		Document doc1 = new Document();
		doc1.put("accountNo", "123456789");
		doc1.put("balance", 3355.00);
		doc1.put("currency", "HKD");

		Document doc2 = new Document();
		doc2.put("accountNo", "987654321");
		doc2.put("balance", 0.00);
		doc2.put("currency", "HKD");

		accountDAO.createAccount(doc1);
		accountDAO.createAccount(doc2);

	}

	public AccountInfoResponse getAccountInfo(String accountNo) {
		AccountInfoResponse res = new AccountInfoResponse();

		Document doc = accountDAO.findByAccountNo(accountNo);
		if (doc != null) {
			res.setAccountNo((String) doc.get("accountNo"));
			res.setAccountBalance((Double) doc.get("balance"));
			res.setCurrency((String) doc.get("currency"));
			res.setStatus("Success");
		} else {
			res.setStatus("Error");
			res.setMessage("Invalid Account");
		}

		return res;
	}

	public TransferMoneyResponse transferMoney(String accountNoFrom, String accountNoTo, Double amount) {
		TransferMoneyResponse res = new TransferMoneyResponse();

		Document accFrom = accountDAO.findByAccountNo(accountNoFrom);
		Document accTo = accountDAO.findByAccountNo(accountNoTo);

		if (accFrom != null && accTo != null) {
			Double remain = (Double) accFrom.get("balance") - amount;
			accFrom.put("balance", remain);

			Double remain2 = (Double) accTo.get("balance") + amount;
			accTo.put("balance", remain2);

			accountDAO.updateAccount(accFrom);
			accountDAO.updateAccount(accTo);

			res.setTransactionID(((Long) System.currentTimeMillis()).toString());
			res.setStatus("Success");

		} else {
			res.setStatus("Error");
			res.setMessage("Invalid Account");
		}

		return res;
	}
}
