package com.foodverse.widgets.input;

import java.awt.Component;
import com.foodverse.utility.core.Widget;
import com.foodverse.utility.layout.Align;
import com.foodverse.utility.layout.EdgeInsets;
import com.foodverse.utility.ui.Colors;
import com.foodverse.utility.ui.TextField;
import com.foodverse.widgets.layout.Column;
import com.foodverse.widgets.text.Label;
import com.foodverse.widgets.text.Paragraph;
import com.foodverse.widgets.text.Label.LabelSize;
import com.foodverse.widgets.text.Paragraph.ParagraphSize;

public final class InputForm extends Widget {

    private final String label;
    private final String caption;
    private final TextField textField;

    public InputForm(String label, String caption, TextField textField) {
        this.label = label;
        this.caption = caption;
        this.textField = textField;
    }

    @Override
    public Component getRef() {
        // Creating form's column widget...
        var formWidget = new Column();
        // Creating text widgets...
        var labelText = new Label(label, LabelSize.S);
        var hintText = new Paragraph(caption, ParagraphSize.S, Colors.gray600);
        // Adding the components of the form to its column...
        formWidget.addWidget(labelText, new EdgeInsets.Builder()
                .bottom(8)
                .build(),
                Align.FIRST_LINE_START);
        formWidget.addComponent(textField, new EdgeInsets.Builder()
                .build(),
                Align.LINE_START);
        formWidget.addWidget(hintText, new EdgeInsets.Builder()
                .top(8)
                .build(),
                Align.LAST_LINE_START);
        return formWidget.getRef();
    }

}
