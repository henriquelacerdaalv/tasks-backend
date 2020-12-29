package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {
	@Test
	public void deveRetornarTrue_paraDatasFuturas(){
		LocalDate date = LocalDate.of(2030, 01, 01);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarFalse_paraDatasPassadas(){
		LocalDate date = LocalDate.of(2010, 01, 01);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrue_paraDataAtual(){
		LocalDate date = LocalDate.now();
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
}
