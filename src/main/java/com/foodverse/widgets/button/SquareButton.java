package com.foodverse.widgets.button;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import com.foodverse.utility.EdgeInsets;
import com.foodverse.utility.core.Button;
import com.foodverse.utility.core.ButtonStyle;
import com.foodverse.utility.core.ButtonTheme;
import com.foodverse.utility.core.TextStyle;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Label.LabelSize;

public final class SquareButton extends Button {

    public SquareButton(String data, ButtonSize buttonSize, ButtonType buttonType,
            Consumer<ActionEvent> onPressed) {
        this(data, true, buttonSize, buttonType, onPressed);
    }

    public SquareButton(String data, boolean isEnabled, ButtonSize buttonSize,
            ButtonType buttonType, Consumer<ActionEvent> onPressed) {
        super(data, onPressed, getButtonStyle(isEnabled, buttonSize, getButtonTheme(buttonType)));
    }

    public static ButtonStyle getButtonStyle(boolean isEnabled, ButtonSize buttonSize,
            ButtonTheme buttonTheme) {
        final TextStyle textStyle;
        final EdgeInsets padding;
        final int borderRadius;
        switch (buttonSize) {
            default:
            case XS:
                textStyle = Label.getTextStyle(LabelSize.XS);
                padding = new EdgeInsets.Builder().symmetric(6, 7).build();
                borderRadius = 8;
                break;
            case S:
                textStyle = Label.getTextStyle(LabelSize.S);
                padding = new EdgeInsets.Builder().symmetric(10, 10).build();
                borderRadius = 8;
                break;
            case M:
                textStyle = Label.getTextStyle(LabelSize.M);
                padding = new EdgeInsets.Builder().symmetric(16, 18).build();
                borderRadius = 8;
                break;
        }
        return new ButtonStyle.Builder()
                .buttonTheme(buttonTheme)
                .textStyle(textStyle)
                .padding(padding)
                .borderRadius(borderRadius)
                .isEnabled(isEnabled)
                .build();
    }

}
