package wn.demo.pociam.controller;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import wn.demo.pociam.dto.NoteRequest;
import wn.demo.pociam.dto.NoteDto;
import wn.demo.pociam.dto.NoteTextDto;
import wn.demo.pociam.service.NoteService;

@Controller
public class WebController {

    Logger logger = LoggerFactory.getLogger(WebController.class);
    private final NoteService noteService;
    
    
    @Autowired
    public WebController(NoteService noteService) {
        this.noteService = noteService;
    }
    
    
    @GetMapping(value="/")
    public String index() {
        return "index";
    }
    
    @GetMapping(value="/login")
    public String login(HttpServletRequest request) {
        logger.info("user id login" + this.getUserId(request));
        return "redirect:/note";
    }
    
    @GetMapping(value="/logout")
    public String inbox(HttpServletRequest request) throws ServletException {
        logger.info("user id logout" + this.getUserId(request));
        request.logout();
        return "redirect:/";
    }
    
    @GetMapping(value="/note")
    public String notes(HttpServletRequest request, Model model) {
        String id = this.getUserId(request);
        List<NoteDto> notes = this.noteService.findNoteByOwner(id);
        model.addAttribute("user", this.getKeycloakSecurityContext(request).getIdToken().getPreferredUsername());
        model.addAttribute("notes", notes);
        return "notes";
    }
    
    @GetMapping(value="/note/{noteid}")
    public String note(HttpServletRequest request, Model model, @PathVariable Long noteid) {
        String id = this.getUserId(request);
        NoteTextDto nt = this.noteService.getNoteText(id, noteid);
        model.addAttribute("note", new NoteRequest(nt.getId(), nt.getTitle(), nt.getText()));
        return "note";
    }
    
    @GetMapping(value="/note/{noteid}/delete")
    public String delete(HttpServletRequest request, Model model, @PathVariable Long noteid) {
        
        this.noteService.delete(this.getUserId(request), noteid);
        return "redirect:/note";
    }
    
    @GetMapping(value="/createnote")
    public String noteForm(HttpServletRequest request, Model model) {
        model.addAttribute("note", new NoteRequest());
        return "createnote";
    }
    
    @PostMapping(value="/note")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String noteSubmit(HttpServletRequest request, Model model, 
            @ModelAttribute NoteRequest noteCreateRequest) {
        logger.info("noteSubmit");
        this.noteService.createNote(this.getUserId(request), noteCreateRequest);
        return "redirect:/note";
    }
    
    @PostMapping(value="/note/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String noteUpdate(HttpServletRequest request, Model model, 
            @ModelAttribute NoteRequest noteRequest) {
        logger.info("noteUpdate");
        this.noteService.update(this.getUserId(request), noteRequest);
        return "redirect:/note";
    }    
    
    
    
    private String getUserId(HttpServletRequest req) {
        return this.getKeycloakSecurityContext(req).getIdToken().getSubject();
    }
    
    
    private KeycloakSecurityContext getKeycloakSecurityContext(HttpServletRequest request)
    {
        return (KeycloakSecurityContext) request
                .getAttribute(KeycloakSecurityContext.class.getName());
    }

    
}
