package com.prabidhinepal.mmccomm.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.prabidhinepal.mmccomm.exceptions.loginmodel.InValidPassWordException;
import com.prabidhinepal.mmccomm.exceptions.loginmodel.InValidPhoneNumberException;

public class LoginModel implements Parcelable {
    private String phone;
    private String password;

    protected LoginModel(Parcel in) {
        phone = in.readString();
        password = in.readString();
    }

    public LoginModel(String phone, String password){
        this.phone = phone;
        this.password = password;
    }

    public LoginModel(){}

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };

    public String getPhone(){return phone;}
    public void setPhone(String phone) throws InValidPhoneNumberException {
        if (isValidPhone(phone)) {
            this.phone = phone;
        }
    }
    public void setPassword(String password) throws InValidPassWordException {
        if(isValidPassword(password))
            this.password = password;
    }
    public Boolean isPassword(String password){
        return this.password.equals(password);
    }
    private Boolean isValidPassword(String pass){
        
        //Write password validation logic here.
        return true;
    }
    private Boolean isValidPhone(String phone) throws InValidPhoneNumberException {
        if(phone.length()<10) throw new InValidPhoneNumberException("Phone number cannot be less than 10 digits");
        if(phone.length()>10) throw new InValidPhoneNumberException("Phone number cannot be more than 10 digits");
        return true;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(phone);
        parcel.writeString(password);
    }
}
