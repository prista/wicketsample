package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class WelcomePage extends WebPage {



    public WelcomePage() {
        add(new Label("lbl", new Model<String>("This is my first Wicket Page")));
    }
}
