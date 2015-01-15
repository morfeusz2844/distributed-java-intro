package pl.edu.amu.dji.jms.lab11.books;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {
    private String isbn;
    private String title;
    private String text;

    public String getIsbn() {
        return isbn;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
