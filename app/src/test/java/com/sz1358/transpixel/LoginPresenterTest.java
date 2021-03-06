package com.sz1358.transpixel;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xxx on 18/12/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    @Mock
    private LoginView view;
    @Mock
    private LoginService service;
    private LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view, service);

    }

    @Test
    public void shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception{
        when(view.getUsername()).thenReturn("");
        presenter.requestLogin();

        verify(view).showUsernameError(R.string.username_error);
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception{
        when(view.getUsername()).thenReturn("james");
        when(view.getPassword()).thenReturn("");
        presenter.requestLogin();

        verify(view).showPasswordError(R.string.password_error);
    }

    @Test
    public void shouldStartMainActivityWhenUsernameAndPasswordAreCorrect() throws Exception {
        when(view.getUsername()).thenReturn("james");
        when(view.getPassword()).thenReturn("bond");
        when(service.login("james", "bond")).thenReturn(true);
        presenter.requestLogin();

        verify(view).startMainActivity();
    }

    @Test
    public void shouldStartMainActivityWhenUsernameAndPasswordAreInvalid() throws Exception {
        when(view.getUsername()).thenReturn("james");
        when(view.getPassword()).thenReturn("bond");
        when(service.login("james", "bond")).thenReturn(false);
        presenter.requestLogin();

        verify(view).showLoginError(R.string.login_failed);
    }
}