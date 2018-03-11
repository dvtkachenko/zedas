package com.zedas.dtkachenko.controller;

import com.zedas.dtkachenko.model.Note;
import com.zedas.dtkachenko.service.NoteService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class NoteManagedBean {

    @Inject
    private NoteService noteService;

    private Note newNote;

    @PostConstruct
    public void initNewNote() {
        newNote = new Note();
    }

    public void createNewNote() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (newNote.getText() == null || newNote.getText().isEmpty()) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Text cannot be null !", ""));
            return;
        }
        try {
            newNote.setCreationDate(new Date());
            noteService.create(newNote);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New note has been created !", ""));
            initNewNote();
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "New note hasn't been created !", e.getMessage()));
        }
    }

    public void deleteNote(Long id) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            noteService.remove(id);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Note has been deleted !", ""));
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Note hasn't been deleted !", e.getMessage()));
        }
    }

    public Note getNewNote() {
        return newNote;
    }

    public void setNewNote(Note newNote) {
        this.newNote = newNote;
    }

    public List<Note> getNotes() {
        return noteService.findAll();
    }
}
