package wn.demo.pociam.controller;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import wn.demo.pociam.InitData;
import wn.demo.pociam.service.NoteService;

@Controller
public class WebController {

    Logger logger = LoggerFactory.getLogger(WebController.class);
    private final HttpServletRequest request;
    private final NoteService noteService;
    
    
    @Autowired
    public WebController(HttpServletRequest request, 
            NoteService noteService) {
        this.request = request;
        this.noteService = noteService;
    }
    
    
    @GetMapping(value="/")
    public String index() {
        return "index";
    }
    
    @GetMapping(value="/login")
    public String inbox(HttpServletRequest request) {
        
        String id = this.getKeycloakSecurityContext(request).getIdToken().getId();
        logger.info("user id " + this.getKeycloakSecurityContext(request).getIdToken());
        return "inbox";
    }
    
    private KeycloakSecurityContext getKeycloakSecurityContext(HttpServletRequest request)
    {
        return (KeycloakSecurityContext) request
                .getAttribute(KeycloakSecurityContext.class.getName());
    }
    private KeycloakSecurityContext getKeycloakSecurityContext()
    {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
    
}
