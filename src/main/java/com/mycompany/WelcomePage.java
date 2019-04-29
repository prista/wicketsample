package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;

public class WelcomePage extends WebPage {

    private static final long serialVersionUID = 1406354671961504008L;

    public WelcomePage() {
        addComponents();
    }

    private void addComponents() {
        Form<?> form = new Form<Object>("form");
        add(form);
    }


}

