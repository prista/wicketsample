package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;

public class ModalWindowExample extends WebPage {

    private ModalWindow modalWindow;

    public ModalWindowExample() {

        modalWindow = new ModalWindow("modalWindow");

        modalWindow.setPageCreator(new ModalWindow.PageCreator() {
            @Override
            public Page createPage() {
                return new WelcomePage();
            }
        });

        modalWindow.setTitle("ModalWindowExample");
        modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
            @Override
            public void onClose(final AjaxRequestTarget target) {
                //Do your stuff on close of modal window here
                System.out.println("I'm closed!");
            }
        });

        add(new AjaxLink<String>("viewLink") {

            @Override
            public void onClick(final AjaxRequestTarget target) {
                // Request target associated with current ajax request.
                modalWindow.show(target);
            }
        });

        // model window is also a component we need to add it in the webpage
        add(modalWindow);

    }
}
