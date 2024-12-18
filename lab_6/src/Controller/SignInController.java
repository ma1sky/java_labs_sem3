package Controller;

import Model.Model;
import View.Colors;
import View.SignInPanel;

import java.io.IOException;


public class SignInController {
    private final Model model;
    private final SignInPanel view;
    private final GeneralController callback;

    public SignInController(Model model, SignInPanel view, GeneralController callback) {
        this.model = model;
        this.view = view;
        this.callback = callback;
        this.view.addSubmitButtonListener(e -> validateUser());
    }

    private void validateUser() {
        String enteredLogin = view.getLogin();
        String enteredPassword = new String(view.getPassword());

        if (validateCredentials(enteredLogin, enteredPassword)) {
            view.setVisible(false);
            try {
                callback.startWorkPanel();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            view.setMessage("Invalid login or password. Try again.");
            view.setMessageColor(Colors.RED);
        }
    }

    private boolean validateCredentials(String login, String password) {
        return login.equals(model.getUser().getLogin()) && password.equals(model.getUser().getPassword());
    }
}