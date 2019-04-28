package com.mycompany;

import com.mycompany.model.USerModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

// user defined (пользовательский) component
public class SamplePanel extends Panel {


    private static final long serialVersionUID = -1591111645810633210L;
    private List<String> genderChoices = new ArrayList<>();

    public SamplePanel(final String id) {
        super(id);
        genderChoices.add("Male");
        genderChoices.add("Female");
        final USerModel uSerModel = new USerModel();
        Form<?> form = new Form("form");

        // Первый аргумент в конструкторе Label, соответствующий wicket: id, назначенному в файле HTML.
        // Второй аргумент - это модель компонента, оболочка для базовых данных, представленных в компоненте.
        TextField<String> text = new TextField("text", new PropertyModel(uSerModel, "name"));
        //The setOutputMarkupId method makes the component eligible for modification via Ajax
        text.setOutputMarkupId(true);

        DropDownChoice<String> gender = new DropDownChoice("gender",
                new PropertyModel(uSerModel, "gender"), genderChoices);
        gender.setOutputMarkupId(true);

        AjaxButton ajaxButton = new AjaxButton("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);

                text.setEnabled(false);
                gender.setEnabled(false);

                // Then AjaxButton is added with the onSubmit callback method
                // that updates the label’s model and the dropdown's model,
                // once ajax request is issued (set property enabled false).
                // Finally, the label and dropdown objects (component)
                // are set as a target for refreshing.
                target.add(text);
                target.add(gender);
            }
        };

/*        Button button = new Button("submit") {
            @Override
            public void onSubmit() {
                super.onSubmit();

                System.out.println("NAME: " + uSerModel.getName());
                System.out.println("GENDER: " + uSerModel.getGender());
            }
        };*/


        add(form);
        form.add(text);
        form.add(gender);
        //form.add(button);
        form.add(ajaxButton);
    }

}

// need to add 'implements Serializable' in USerModel
// 1. Let's define all components

// TextField binding with the "name" field in our USerModel pojo
// DropDown binding with the "gender" field in our USerModel pojo
// когда мы что-то вводим в инпут, автоматом в Pojo сетается это значение (из за байндинга)

// 2. We need to add these components in the web page
// форма у нас сразу в боди лежала,
// ее мы должны добавить непосредственно на web page
// уже в form мы пихаем ее содержимое (компоненты другие)

