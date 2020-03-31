package com.hsbc.financetest.dao;

import javax.annotation.Resource;

import org.dizitart.no2.Document;
import org.dizitart.no2.filters.Filters;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

	@Resource
	NitriteDB dbHelper;

	public void createAccount(Document doc) {
		dbHelper.add(doc);
	}

	public Document findByAccountNo(String accountNo) {
		return dbHelper.findByFilter(Filters.eq("accountNo", accountNo));
	}

	public void updateAccount(Document doc) {
		dbHelper.update(Filters.eq("accountNo", doc.get("accountNo")), doc);
	}

}
