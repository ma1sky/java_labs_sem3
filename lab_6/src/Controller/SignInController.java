package Controller;

import Model.User;
import View.Colors;
import View.SignInFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInController {
    private final User user;
    private final SignInFrame view;

    public SignInController(User user, SignInFrame view) {
        this.user = user;
        this.view = view;

        this.view.addSubmitButtonListener(e -> validateUser());
    }

    private void validateUser() {
        String enteredLogin = view.getLogin();
        String enteredPassword = view.getPassword();

        if (validateCredentials(enteredLogin, enteredPassword)) {
            view.setMessage("Login successful!");
            view.setMessageColor(Colors.GREEN);
        } else {
            view.setMessage("Invalid login or password. Try again.");
            view.setMessageColor(Colors.RED);
        }
    }

    private boolean validateCredentials(String login, String password) {
        return login.equals(user.getLogin()) && password.equals(user.getPassword());
    }
}