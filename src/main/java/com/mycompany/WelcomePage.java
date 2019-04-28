package com.mycompany;

import org.apache.wicket.markup.html.WebPage;

public class WelcomePage extends WebPage {

    private static final long serialVersionUID = 1406354671961504008L;

    public WelcomePage() {
        // panel is a reusable component that holds markup and other components
        add(new SamplePanel("panel"));
        add(new SamplePanel("panel1"));
    }
}

