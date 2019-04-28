package com.mycompany;

import com.mycompany.model.USerModel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

public class WelcomePage extends WebPage {

    // need to add 'implements Serializable' in USerModel
    private static final long serialVersionUID = 1406354671961504008L;
    private List<String> genderChoices = new ArrayList<>();

    public WelcomePage() {
        genderChoices.add("Male");
        genderChoices.add("Female");
        // когда переменная юзается в inner class то делаем ее final (уточинть)
        final USerModel uSerModel = new USerModel();

        // 1. Let's define all components
        Form<?> form = new Form("form");

        // TextField binding with the "name" field in our USerModel pojo
        // DropDown binding with the "gender" field in our USerModel pojo
        // когда мы что-то вводим в инпут, автоматом в Pojo сетается это значение (из за байндинга)
        TextField<String> text = new TextField("text", new PropertyModel(uSerModel, "name"));

        DropDownChoice<String> gender = new DropDownChoice("gender",
                new PropertyModel(uSerModel, "gender"), genderChoices);
        // обычно 3й параметр берется из базы

        Button button = new Button("submit") {
            @Override
            public void onSubmit() {
                super.onSubmit();

                System.out.println("NAME: " + uSerModel.getName());
                System.out.println("GENDER: " + uSerModel.getGender());
            }
        };


        // 2. We need to add these components in the web page

        // форма у нас сразу в боди лежала,
        // ее мы должны добавить непосредственно на web page
        add(form);
        // уже в form мы пихаем ее содержимое
        form.add(text);
        form.add(gender);
        form.add(button);
    }
}
