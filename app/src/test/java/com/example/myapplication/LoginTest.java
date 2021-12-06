package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.myapplication.loginmodule.LoginActivity;
import com.example.myapplication.loginmodule.LoginModel;
import com.example.myapplication.loginmodule.LoginPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
    @Mock
    LoginActivity view;
    @Mock
    LoginModel model;

    @Test
    public void testAuthenticateLogin1(){
        String email = null;
        String password = "a";
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.authenticateLogin(email, password);
        verify(view).displayError("Blank Fields");
    }

    @Test
    public void testAuthenticateLogin2(){
        String email = "";
        String password = "a";
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.authenticateLogin(email, password);
        verify(view).displayError("Blank Fields");
    }
    @Test
    public void testAuthenticateLogin3(){
        String email = "a";
        String password = null;
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.authenticateLogin(email, password);
        verify(view).displayError("Blank Fields");
    }
    @Test
    public void testAuthenticateLogin4(){
        String email = "a";
        String password = "";
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.authenticateLogin(email, password);
        verify(view).displayError("Blank Fields");
    }

    @Test
    public void testAuthenticateLogin5(){
        String email = "a";
        String password = "a";
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.authenticateLogin(email, password);
        verify(model).loginUser(email, password, view, presenter);
    }

    @Test
    public void testDetermineSuccess1(){
        String result = "Success";
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.determineSuccess(result);
        verify(model).getAccountType(presenter);
    }

    @Test
    public void testDetermineSuccess2(){
        String result = "Fail";
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.determineSuccess(result);
        verify(view).displayError("*Credentials Not Valid");
    }

    @Test
    public void testStartRouting(){
        int type = 1;
        LoginPresenter presenter = new LoginPresenter(model,view);
        presenter.startRouting(type);
        verify(view).router(type);
    }
}
