package wn.demo.pociam.dto;

public class NoteTextDto {

    private Long id;
    private String title;
    private String text;
    public NoteTextDto(Long id, String text) {
        super();
        this.id = id;
        this.text = text;
    }
    
    public NoteTextDto(Long id, String title, String text) {
        super();
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public NoteTextDto() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
}
