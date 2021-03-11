package org.ppr.patterns.mvc.view;

import org.junit.Assert;
import org.junit.Test;

public class AppUITest {
	
	
	@Test
	public void completeNumberMaskTest() {
		
		AppUI appUI = new AppUI();
		
		Assert.assertEquals("00424786931", appUI.completeNumberMask("424786931", 11));
		Assert.assertEquals("01234567890123", appUI.completeNumberMask("1234567890123", 14));
	}
}
