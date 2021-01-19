package wn.demo.pociam;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import wn.demo.pociam.dto.NoteCreateRequest;
import wn.demo.pociam.dto.NoteDto;
import wn.demo.pociam.entity.NoteText;
import wn.demo.pociam.repository.NoteTextRepository;
import wn.demo.pociam.service.NoteService;

@Component
public class InitData implements CommandLineRunner{

    Logger logger = LoggerFactory.getLogger(InitData.class);
    
    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteTextRepository noteTextRepository;
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("Init Data");
        NoteCreateRequest req = new NoteCreateRequest("title test", "this is a note testing");
        NoteDto noteDto = this.noteService.createNote("testuser", req);
        
        Optional<NoteText> op = this.noteTextRepository.findNoteTextByIdAndNoteUserid(
                noteDto.getId(), "testuser");
        if(op.isPresent()) {
            logger.info("nt retrieved " + op.get());
        }
        
    }

}
