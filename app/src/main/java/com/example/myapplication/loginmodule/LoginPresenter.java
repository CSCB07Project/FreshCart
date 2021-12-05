package com.example.myapplication.loginmodule;

public class LoginPresenter implements Contract.Presenter {
    private LoginModel model;
    private LoginActivity view;
    public LoginPresenter(LoginModel m, LoginActivity v){
        this.model = m;
        this.view = v;
    }
    @Override
    public void authenticateLogin(String email, String password) {
        if(email.equals(null) || email.equals("") || password.equals(null) || password.equals("") ){
            view.displayError("Blank Fields");
        }
        else{
            model.loginUser(email, password, view, this);
        }
    }

    @Override
    public void determineSuccess(String result) {
        if(result.equals("Success")){
            model.getAccountType(this);
        }
        else{
            view.displayError("*Credentials Not Valid");
        }
    }

    @Override
    public void startRouting(int type){
        view.router(type);
    }
}
