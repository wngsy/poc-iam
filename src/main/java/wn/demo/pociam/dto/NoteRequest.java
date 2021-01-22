package wn.demo.pociam.dto;

public class NoteRequest {

    private Long id;
    private  String title;
    private String text;
    public NoteRequest(String title, String text) {
        super();
        this.title = title;
        this.text = text;
    }
    
    
    
    public NoteRequest(Long id, String title, String text) {
        super();
        this.id = id;
        this.title = title;
        this.text = text;
    }



    public NoteRequest() {}

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    
}
