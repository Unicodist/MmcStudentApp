package com.prabidhinepal.mmccomm.validation.view;

import com.google.android.material.textfield.TextInputLayout;
import com.prabidhinepal.mmccomm.exceptions.registermodel.InValidNameException;
import com.prabidhinepal.mmccomm.validation.data.NameValidation;

public class NameValidationView {
    public void validate(TextInputLayout textInputLayout) throws InValidNameException {
        String nameText = textInputLayout.getEditText().getText().toString();
        NameValidation.validate(nameText);
    }
}
