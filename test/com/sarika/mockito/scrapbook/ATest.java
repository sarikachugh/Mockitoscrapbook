package com.sarika.mockito.scrapbook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class ATest {
	
	@Mock
	B b;
	private A a;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		a = new A(b);
	}
	
	@Test
	public void usesVoidMethodShouldCallTheVoidMethod() throws Exception {
	doNothing().when(b).voidmethod();
	assertSame(1, a.usesVoidMethod());
	verify(b).voidmethod();
	}

	@Test(expected=RuntimeException.class)
	public void usesVoidMethodShouldThrowRunTimeException() throws Exception {
		doThrow(Exception.class).when(b).voidmethod();
		a.usesVoidMethod();
	}
	
	@Test(expected=RuntimeException.class)
	public void testConsecutiveCalls() throws Exception {
		doNothing().doThrow(Exception.class).when(b).voidmethod();
		a.usesVoidMethod();
		verify(b).voidmethod();
		a.usesVoidMethod();
	}
	
}
