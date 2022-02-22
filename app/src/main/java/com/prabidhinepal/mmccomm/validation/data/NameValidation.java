package com.prabidhinepal.mmccomm.validation.data;

import com.prabidhinepal.mmccomm.ConstantValues;
import com.prabidhinepal.mmccomm.exceptions.registermodel.InValidNameException;

public class NameValidation {
    public static void validate(String name) throws InValidNameException {
        name = name.trim();
        if(name.length()< ConstantValues.NameMinLength) {throw new InValidNameException("Too less characters for a name"); }
        if(name.length()>ConstantValues.NameMaxLength){throw new InValidNameException("Max limit of number of characters");}
        if(!name.matches("^[a-zA-Z]*$")) {throw new InValidNameException("Cannot contain anything other than alphabets");}
    }
}
