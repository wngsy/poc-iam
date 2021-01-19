package wn.demo.pociam.dto;

public class NoteCreateRequest {

    private  String title;
    private String text;
    public NoteCreateRequest(String title, String text) {
        super();
        this.title = title;
        this.text = text;
    }
    
    public NoteCreateRequest() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
}
