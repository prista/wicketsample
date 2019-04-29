package com.mycompany;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.Model;

public class WelcomePage extends WebPage {

    private static final long serialVersionUID = 1406354671961504008L;

    // this variable will tell me whether i have clicked the viewLink or editLink
    // and this i will get from the constructor
    private String action;

    public WelcomePage(String pAction) {
        this.action = pAction;
        addComponents();
    }

    private void addComponents() {
        Form<?> form = new Form<Object>("form");

        Fragment fragment;
        // for adding fragment, first we'll define a condition, here we'll check what is the action
        if (action.equalsIgnoreCase("view")) {
            // 3 parameters:
            // 1) container in which we need to add the fragment
            // 2) wicketID of your fragment that you want to add
            // 3) mark-up provider, in our case - WelcomePage
            fragment = new Fragment("container", "viewFragment", this);
            fragment.add(new Label("label", new Model<String>("Hello World")));
        } else {
            fragment = new Fragment("container", "editFragment", this);
            fragment.add(new TextField<String>("textField", new Model<String>("Hello World")));
        }

        form.add(fragment);
        add(form);
    }


}

