package com.cathay.test.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CoindeskDto {

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime time;
	private Map<String, Currency> currencies;

	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public Map<String, Currency> getCurrencies() {
		return currencies;
	}
	public void setCurrencies(Map<String, Currency> currencies) {
		this.currencies = currencies;
	}

	public static class Currency {

		private String chName;
		private float rate;
		
		public String getChName() {
			return chName;
		}
		public void setChName(String chName) {
			this.chName = chName;
		}
		public float getRate() {
			return rate;
		}
		public void setRate(float rate) {
			this.rate = rate;
		}
	}
}
