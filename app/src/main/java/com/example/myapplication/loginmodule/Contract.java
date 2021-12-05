package com.example.myapplication.loginmodule;


public interface Contract {
    public interface Model{
        public void loginUser(String email, String password, LoginActivity view, LoginPresenter presenter);
        public void getAccountType(LoginPresenter presenter);
    }
    public interface View{
        public String getUsername();
        public String getPassword();
        public void displayError(String message);
        public void router(int type);
    }
    public interface Presenter{
        public void authenticateLogin(String email, String password);
        public void determineSuccess(String result);
        public void startRouting(int type);
    }
}
