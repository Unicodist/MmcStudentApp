package com.prabidhinepal.mmccomm.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.prabidhinepal.mmccomm.exceptions.registermodel.InValidNameException;
import com.prabidhinepal.mmccomm.validation.data.NameValidation;
import com.prabidhinepal.mmccomm.validation.data.PasswordValidation;
import com.prabidhinepal.mmccomm.validation.data.PhoneValidation;

public class RegisterModel implements Parcelable {
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public RegisterModel(String name, String phone, String address, String password, String confirm) throws InValidNameException {
            validateFields(name,phone,address,password,confirm);
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.password = password;
            this.confirm = confirm;
    }

    private String name;
    private String phone;
    private String address;
    private String password;
    private String confirm;

    protected RegisterModel(Parcel in) {
        name = in.readString();
        phone = in.readString();
        address = in.readString();
        password = in.readString();
        confirm = in.readString();
    }

    public static final Creator<RegisterModel> CREATOR = new Creator<RegisterModel>() {
        @Override
        public RegisterModel createFromParcel(Parcel in) {
            return new RegisterModel(in);
        }

        @Override
        public RegisterModel[] newArray(int size) {
            return new RegisterModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(address);
        parcel.writeString(password);
        parcel.writeString(confirm);
    }
    private void validateFields(String name, String phone, String address, String password, String confirm) throws InValidNameException {
        NameValidation.validate(name);
        PasswordValidation.validate(phone);
        PhoneValidation.validate(phone);
    }
}
