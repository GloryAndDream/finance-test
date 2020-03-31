package com.hsbc.financetest.DAO;

import javax.annotation.Resource;

import org.dizitart.no2.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hsbc.financetest.dao.AccountDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountDAOTest {

	@Resource
	AccountDAO accountDAO;

	@Before
	public void setup() {
		Document doc1 = new Document();
		doc1.put("accountNo", "33893389");
		doc1.put("balance", 3355.00);
		doc1.put("currency", "HKD");

		Document doc2 = new Document();
		doc2.put("accountNo", "99885544");
		doc2.put("balance", 0.00);
		doc2.put("currency", "HKD");

		accountDAO.createAccount(doc1);
		accountDAO.createAccount(doc2);

	}

	@Test
	public void testFindByAccount() {
		Assert.assertTrue(this.accountDAO.findByAccountNo("33893389") != null);
		Assert.assertTrue((Double) (this.accountDAO.findByAccountNo("33893389").get("balance")) > 0);
	}

	@Test
	public void testFindByInvalidAccount() {
		Assert.assertTrue(this.accountDAO.findByAccountNo("1111") == null);
	}
}
