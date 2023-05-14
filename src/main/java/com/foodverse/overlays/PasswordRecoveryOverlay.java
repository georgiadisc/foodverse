package com.foodverse.overlays;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.swing.JPanel;
import javax.xml.validation.Validator;

import com.foodverse.models.User;
import com.foodverse.utility.common.UIConstants;
import com.foodverse.utility.input.InputValidation;
import com.foodverse.utility.navigation.Overlay;
import com.foodverse.utility.navigation.Router;
import com.foodverse.utility.system.Database;
import com.foodverse.utility.ui.TextField;
import com.foodverse.utility.ui.Button.ButtonSize;
import com.foodverse.utility.ui.Button.ButtonType;
import com.foodverse.widgets.button.RectButton;
import com.foodverse.widgets.input.InputForm;
import com.foodverse.widgets.modal.Alert;
import com.foodverse.widgets.text.Heading;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Heading.HeadingSize;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class PasswordRecoveryOverlay extends Overlay {

    private final Component component;

    //This object will help us to produce a random number in order to show a random recovery question
    private Random random = new Random();

    // Getting a reference to the database...
    private final Database db = Database.getInstance();

    //Validation for inputs
    private final InputValidation validator = InputValidation.getInstance();


    /*
     * private Row signInHeadingRow = new Row(); private Row emailInputRow = new Row(); private Row
     * passwordInputRow = new Row();
     */

    public PasswordRecoveryOverlay(Optional<User> user) {
        super(500, 400);

        var configuration = db.getConfiguration();
        var randomQuestion = "";
        
        if(!configuration.isEmpty())
        {
            var randomIndex = random.nextInt(2); //0 inclusive, 2 exclusive
            randomQuestion = configuration.get().recoveryQuestions().get(randomIndex);
        }

        // Heading
        var panel = new JPanel();
        var passwordRecoveryHeading = new Heading("Password Recovery", HeadingSize.XXL);
        var explanationParagraph = new Paragraph("It seems that you forgot your password. Answer the following question to change it.", ParagraphSize.M);
        var textAnswerInput = new TextField();
        var answer = new InputForm(randomQuestion, "", textAnswerInput);
        var continueButton = new RectButton(
            "Continue",
            ButtonSize.L,
            ButtonType.PRIMARY,
            e -> {
                    boolean isValidAnswer = checkValidityOfRecoveryAnswer(textAnswerInput.getText());

                    if(isValidAnswer)
                    {
                        //compare with the value that user gave on sign up process
                        //var correctAnswer = user           
                    }
                    else
                    {
                        Router.openOverlay(new Alert(UIConstants.INVALID_RECOVERY_ANSWERS_INPUT_TITLE,
                        UIConstants.INVALID_RECOVERY_ANSWERS_INPUT_DESCRIPTION));
                    }
                }
            
            );

       
        panel.add(passwordRecoveryHeading.getRef());
        panel.add(explanationParagraph.getRef());
        panel.add(answer.getRef());
        panel.add(continueButton.getRef());
        panel.setOpaque(false);
        component = panel;
    }

    private boolean checkValidityOfRecoveryAnswer(String anAnswer)
    {
        return validator.isAnswersValid(anAnswer);
    }

    @Override
    public Component getRef() {
        return component;
    }


}
