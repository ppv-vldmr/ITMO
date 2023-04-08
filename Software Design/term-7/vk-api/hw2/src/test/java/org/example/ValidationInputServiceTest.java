package org.example;

import org.junit.Before;
import org.junit.Test;
import org.example.service.ValidationInputService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ValidationInputServiceTest {
    private ValidationInputService validationInputService;

    @Before
    public void init() {
        validationInputService = new ValidationInputService();
    }

    @Test
    public void argsIsNullTest() {
        assertFalse(validationInputService.validationInput(null));
    }

    @Test
    public void contentArgsIsNullTest() {
        assertFalse(validationInputService.validationInput(new String[]{null}));
    }

    @Test
    public void invalidArgsLengthTest() {
        assertFalse(validationInputService.validationInput(new String[]{"#test"}));
        assertFalse(validationInputService.validationInput(new String[]{"#test", "4", "extra"}));
    }

    @Test
    public void firstArgIsNotHashtagTest() {
        assertFalse(validationInputService.validationInput(new String[]{"not hashtag", "4"}));
    }

    @Test
    public void secondArgIsNotNumberTest() {
        assertFalse(validationInputService.validationInput(new String[]{"#test", "Nan"}));
    }

    @Test
    public void hashtagWithoutContentTest() {
        assertFalse(validationInputService.validationInput(new String[]{"#", "4"}));
    }

    @Test
    public void invalidSecondArgRangeTest() {
        assertFalse(validationInputService.validationInput(new String[]{"#test", "0"}));
        assertFalse(validationInputService.validationInput(new String[]{"#test", "25"}));
    }

    @Test
    public void validArgsTest() {
        assertTrue(validationInputService.validationInput(new String[]{"#test", "1"}));
        assertTrue(validationInputService.validationInput(new String[]{"#first#second", "2"}));
    }
}
