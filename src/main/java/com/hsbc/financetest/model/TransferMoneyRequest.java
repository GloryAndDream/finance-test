package com.hsbc.financetest.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferMoneyRequest {

	@JsonProperty(required = true)
	@NotNull
	private String accountNoFrom;

	@JsonProperty(required = true)
	@NotNull
	private String accountNoTo;

	@JsonProperty(required = true)
	@NotNull
	private Double amount;

	public String getAccountNoFrom() {
		return accountNoFrom;
	}

	public void setAccountNoFrom(String accountNoFrom) {
		this.accountNoFrom = accountNoFrom;
	}

	public String getAccountNoTo() {
		return accountNoTo;
	}

	public void setAccountNoTo(String accountNoTo) {
		this.accountNoTo = accountNoTo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
