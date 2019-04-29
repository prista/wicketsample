package com.mycompany;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class WelcomePage extends WebPage {

    private static final long serialVersionUID = 1406354671961504008L;

    public WelcomePage() {
        // panel is a reusable component that holds markup and other components
/*        add(new SamplePanel("panel"));
        add(new SamplePanel("panel1"));*/

        Form<?> form = new Form<Object>("form");
        TextField<String> textField = new TextField<>("textField", new Model<>());
        textField.setOutputMarkupId(true);
        // Render a placeholder tag when the component is NOT VISIBLE.
        // This method will also call setOutputMarkupId(true)
        textField.setOutputMarkupPlaceholderTag(true);

        form.add(textField);

        form.add(new AjaxButton("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                if (textField.isVisible()) {
                    textField.setVisible(false);
                } else {
                    textField.setVisible(true);
                }
                target.add(textField);
            }
        });
        add(form);
    }
}

