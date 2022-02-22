package com.prabidhinepal.mmccomm.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.prabidhinepal.mmccomm.exceptions.loginmodel.InValidPhoneNumberException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LoginModelTest {
    private final String _shortPhone = "56";
    private final String _longPhone = "9889876765434";
    private final String _exceptionMessage = "Phone number cannot be less than 10 digits";
    @Test
    public void givePhoneLengthLessThanTen_SetPhoneMethod_ThrowsNotAValidPhoneNumberException(){
        LoginModel model = new LoginModel();
        assertThrows(InValidPhoneNumberException.class, ()->{
            model.setPhone(_shortPhone);
        });
    }
    @Test
    public void givePhoneLengthMoreThanTen_SetPhoneMethod_ThrowsNotAValidPhoneNumberException(){
        LoginModel model = new LoginModel();
        assertThrows(InValidPhoneNumberException.class, ()->{
            model.setPhone(_longPhone);
        });
    }
}
