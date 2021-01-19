package wn.demo.pociam.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class NoteText {

    @Id
    private Long id;
    @Lob
    private String text;
    
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Note note;

    public NoteText(Long id, String text, Note note) {
        super();
        this.id = id;
        this.text = text;
        this.note = note;
    }
    
    public NoteText(String text, Note note) {
        super();
        this.text = text;
        this.note = note;
    }
    
    public NoteText() {}

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

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteText [id=" + id + ", text=" + text + "]";
    }
    
    
}
