package com.tutorial.navomatic;

import org.apache.wicket.markup.html.WebPage;

public class Page3 extends WebPage {

    public Page3() {
        add(new NavomaticBorder("navomaticBorder"));
    }
}
