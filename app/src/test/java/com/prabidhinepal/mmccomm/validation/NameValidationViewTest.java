package com.prabidhinepal.mmccomm.validation;

import com.prabidhinepal.mmccomm.exceptions.registermodel.InValidNameException;
import com.prabidhinepal.mmccomm.validation.data.NameValidation;

import org.junit.Assert;
import org.junit.Test;

public class NameValidationViewTest {
    private final String shortNAme = "Ash";
    private final String longName = "Ashishsshshshshshshshhshshshs";
    @Test
    public void givenShortName_validateMethod_throwsInValidNameException(){
        Assert.assertThrows(InValidNameException.class, ()->{
            NameValidation.validate(shortNAme);});
    }
    @Test
    public void givenLongName_validateMethod_throwsInValidNameException(){
        Assert.assertThrows(InValidNameException.class, ()->{NameValidation.validate(longName);});
    }
}
