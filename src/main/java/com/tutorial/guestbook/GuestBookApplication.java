package com.tutorial.guestbook;


import org.apache.wicket.protocol.http.WebApplication;

public class GuestBookApplication extends WebApplication {
    public GuestBookApplication() {
    }

    @Override
    public Class getHomePage() {
        return GuestBook.class;
    }
}
