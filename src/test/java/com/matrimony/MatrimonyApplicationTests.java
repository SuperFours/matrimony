package com.matrimony;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MatrimonyApplicationTests {

	@Test
	public void applicationTest() {
		MatrimonyApplication.main(new String[] {});
	    assertTrue(true);
	}

}
