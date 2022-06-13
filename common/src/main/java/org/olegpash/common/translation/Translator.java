package org.olegpash.common.translation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import static org.olegpash.common.translation.GoogleTranslateApi.getTranslation;


public class Translator {
    private ResourceBundle messages;
    private String language;
    private String country;
    private String source;

    public ResourceBundle getMessages() {
        return messages;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getSource() {
        return source;
    }

    public Translator(String language, String country, String source) throws IOException {
        setLanguage(language, country, source);
    }

    public void setLanguage(String language, String country, String source) throws IOException {
        if (source.equals("file")) {
            Locale loc = new Locale(country, language);
            this.language = language;
            this.country = country;
            this.source = source;
            this.messages = ResourceBundle.getBundle("localization.locale", loc);
        } else {
            String text = "Hello world!";
            System.out.println("Translated text: " + getTranslation("en", "uk", text));

        }
    }


    public static void main(String[] args) throws IOException {
        Translator translator = new Translator("Russia", "ru", "file");
        System.out.println(new String(translator.messages.getString("app.menu").getBytes(StandardCharsets.ISO_8859_1)));
        translator.setLanguage("English", "en", "file");
        System.out.println(translator.messages.getString("app.menu"));
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Translator that = (Translator) o;
        return messages.equals(that.messages) && language.equals(that.language) && country.equals(that.country) && source.equals(that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, language, country, source);
    }

    @Override
    public String toString() {
        return "Translator{" +
                "messages=" + messages +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
