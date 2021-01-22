package wn.demo.pociam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wn.demo.pociam.dto.NoteRequest;
import wn.demo.pociam.dto.NoteDto;
import wn.demo.pociam.dto.NoteTextDto;
import wn.demo.pociam.entity.Note;
import wn.demo.pociam.entity.NoteText;
import wn.demo.pociam.repository.NoteRepository;
import wn.demo.pociam.repository.NoteTextRepository;

@Service
public class NoteService {

    private NoteRepository noteRepository;
    private NoteTextRepository noteTextRepository;
    private Logger logger = LoggerFactory.getLogger(NoteService.class);
    
    
    
    @Autowired
    public NoteService(NoteRepository noteRepository, NoteTextRepository noteTextRepository) {
        super();
        this.noteRepository = noteRepository;
        this.noteTextRepository = noteTextRepository;
    }

    @Transactional
    public NoteDto createNote(String userId, NoteRequest noteCreateRequest) {
        String title = (noteCreateRequest.getTitle() != null && noteCreateRequest.getTitle().length() > 0 ) ?
                noteCreateRequest.getTitle() : 
                    noteCreateRequest.getText().substring(0, 
                            noteCreateRequest.getText().length() > 30 ? 30 : noteCreateRequest.getText().length());
        Note note = new Note(userId,title);
        note = this.noteRepository.save(note);
        NoteText noteText = new NoteText(noteCreateRequest.getText(), note);
        noteText = this.noteTextRepository.save(noteText);
        
        logger.info(note.toString());
        logger.info(noteText.toString());
        return this.fromNote(note);
    }
    
    @Transactional
    public void delete(String userid, Long noteid) {
        
        Note note = this.noteRepository.findNoteByUseridAndId(userid, noteid)
                .orElseThrow(()-> new RuntimeException("User note not found"));
        
        this.noteTextRepository.deleteById(note.getId());
        this.noteRepository.delete(note);
        
    }
    
    @Transactional
    public void updateTitle(String userid, Long id, String title) {
        Note note = this.noteRepository.findNoteByUseridAndId(userid, id)
                .orElseThrow(()-> new RuntimeException("User note not found"));
        note.setTitle(title);
        this.noteRepository.save(note);
    }
    
    @Transactional
    public void updateText(String userid, Long id, String text) {
        NoteText noteText = this.noteTextRepository.findNoteTextByIdAndNoteUserid(id, userid)
                .orElseThrow(()-> new RuntimeException("NoteText not found"));
        noteText.setText(text);
        this.noteTextRepository.save(noteText);
    }
    @Transactional
    public void update(String userid, NoteRequest noteRequest) {
        Note note = this.noteRepository.findNoteByUseridAndId(userid, noteRequest.getId())
                .orElseThrow(()-> new RuntimeException("User note not found"));
        note.setTitle(noteRequest.getTitle());
        
        NoteText nt = this.noteTextRepository.findById(note.getId())
                .orElseThrow(()-> new RuntimeException("User note not found"));
        nt.setText(noteRequest.getText());
        
        this.noteRepository.saveAndFlush(note);
        this.noteTextRepository.save(nt);
    }
    
    
    
    public List<NoteDto> findNoteByOwner(String userid) {
        List<Note> notes = this.noteRepository.findByUseridOrderByAuditUpdatedOn(userid);
        return notes
            .stream()
            .map(o->this.fromNote(o))
            .collect(Collectors.toList());
        
    }
    
    
    public NoteTextDto getNoteText(String userid, Long id) {
        NoteText noteText = this.noteTextRepository.findNoteTextByIdAndNoteUserid(id, userid)
                .orElseThrow(()-> new RuntimeException("NoteText not found"));
        
        return this.fromNoteText(noteText);
    }
    
    
    
    public NoteDto fromNote(Note note) {
        NoteDto dto = new NoteDto(note.getId(), 
                note.getTitle(), 
                note.getAudit().getCreatedOn(), 
                note.getAudit().getUpdatedOn());
        return dto;
    }
    
    
    public NoteTextDto fromNoteText(NoteText noteText) {
        NoteTextDto dto = new NoteTextDto(noteText.getId(), noteText.getNote().getTitle(),
                noteText.getText());
        return dto;
    }
}