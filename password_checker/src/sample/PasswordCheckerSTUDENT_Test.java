package sample;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Talal Brek
 *
 */
public class PasswordCheckerSTUDENT_Test {
	ArrayList<String> invalidPasswordsArray;
	ArrayList<String> validPasswordsArray;
	String tooShort = "Talal";
	String invalidSequence = "!Taaaaaalal1";
	String noUpper = "!talal1";
	String noLower = "!TALAL1";
	String noSpecial = "Talal1";
	String noDigit = "!Talalal";
	String strong = "!Talal5789";
	String weak = "!Talal1";

	@Before
	public void setUp() throws Exception {

		String[] containsInvalidPwd = {"BBBriana@54", "Threeww2","%KIALO4", "%kialo4","%DonzoBoi", "Boi"};
		invalidPasswordsArray = new ArrayList<String>();
		invalidPasswordsArray.addAll(Arrays.asList(containsInvalidPwd));

		String[] allValidPasswords = {"!Talal5789", "T@lal&3", "%54Talal"};
		validPasswordsArray = new ArrayList<String>();
		validPasswordsArray.addAll(Arrays.asList(allValidPasswords));
	}

	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength(tooShort);
			}
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		Throwable exception = assertThrows(NoUpperAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasUpperAlpha(noUpper);
			}
		});
		assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		Throwable exception = assertThrows(NoLowerAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasLowerAlpha(noLower);
			}
		});
		assertEquals("The password must contain at least one lower case alphabetic character", exception.getMessage());

	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(weak));
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		Throwable exception = assertThrows(InvalidSequenceException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasSameCharInSequence(invalidSequence);
			}
		});
		assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		Throwable exception = assertThrows(NoDigitException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasDigit(noDigit);
			}
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		assertTrue(PasswordCheckerUtility.isValidPassword(strong));
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(validPasswordsArray);
		assertTrue(results.isEmpty());

	}
	
}