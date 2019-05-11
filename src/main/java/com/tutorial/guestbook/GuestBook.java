package com.tutorial.guestbook;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.value.ValueMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * A simple "guest book" example that allows visitors to the page to add a comment and see the
 * comments others have added.
 *
 * For unit testing, added a parameter to clear the commentList.
 *
 * @author Jonathan Locke
 * @author Martijn Dashorst
 */

public final class GuestBook extends WebPage {

    /** A global list of all comments from all users across all sessions */
    private static final List<Comment> commentList = Collections.synchronizedList(new ArrayList<Comment>());

    public static int counter = 0;


    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public GuestBook() {
        /*
            The page consists of a form for entering new items to the guestbook
            and a list of repeating markup for showing the guestbook entries.
        */
        counter++;

        // Add comment form
        add(new CommentForm("commentForm"));

        // Add commentListView of existing comments
        add(new PropertyListView<Comment>("comments", commentList) {
            @Override
            public void populateItem(final ListItem<Comment> listItem) {
                listItem.add(new Label("date"));
                listItem.add(new MultiLineLabel("text"));
                // этот метод дергается при рендеринге страницы (при каждом добавлении нового коммента)
                System.out.println("I WAS CALLED!!" + commentList);
            }
        }).setVersioned(false);
        //this.isPageStateless();
        System.out.println("CONSTRUCTOR CALLED " + counter);
        // а вот конструктор единожды вызывается, при инициализации
    }

    /**
     * A form that allows a user to add a comment.
     */
    public final class CommentForm extends Form<ValueMap> {
        public CommentForm(final String id) {
            // Construct form with no validation listener
            super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
            // this is just to make the unit test happy
            setMarkupId("commentForm");
            // Add text entry widget
            add(new TextArea<String>("text").setType(String.class));
            // Add simple automated spam prevention measure.
            /*add(new TextField<String>("comment").setType(String.class));*/
            System.out.println("VYZOV!!");
        }

        /**
         * Show the resulting valid edit
         */
        @Override
        public final void onSubmit() {
            ValueMap values = getModelObject();
            // check if the honey pot is filled
            if (StringUtils.isNotBlank((String)values.get("comment"))) {
                error("Caught a spammer!!!");
                return;
            }
            // Construct a copy of the edited comment
            Comment comment = new Comment();
            // Set date of comment to add
            comment.setDate(new Date());
            comment.setText((String)values.get("text"));
            commentList.add(0, comment);
            // Clear out the text component
            values.put("text", "");
        }
    }

    /**
     * Clears the comments.
     */
    public static void clear() {
        commentList.clear();
    }
}