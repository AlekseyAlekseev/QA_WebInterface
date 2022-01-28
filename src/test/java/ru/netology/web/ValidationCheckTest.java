package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ValidationCheckTest {

    @Test
    public void shouldEmptyFieldName() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(Condition.exactText(
                "Поле обязательно для заполнения"
        ));
    }

    @Test
    public void shouldInvalidName() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Aleksey Alekseev");
        form.$("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(Condition.exactText(
                "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."
        ));
    }


    @Test
    public void shouldEmptyFieldPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Алексей");
        form.$("button").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldInvalidPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Алексей");
        form.$("[data-test-id=phone] input").setValue("Аааа");
        form.$("button").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(Condition.exactText(
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."
        ));
    }

    @Test
    public void shouldCheckboxNotSet() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Алексей");
        form.$("[data-test-id=phone] input").setValue("+79012345678");
        form.$("button").click();
        $("[data-test-id=agreement]").shouldHave(Condition.attribute("class",
                "checkbox checkbox_size_m checkbox_theme_alfa-on-white input_invalid"));
    }
}
