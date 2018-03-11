package com.zedas.dtkachenko.service;

import com.zedas.dtkachenko.model.Note;
import com.zedas.dtkachenko.repository.NoteRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class NoteService {

    @Inject
    private NoteRepository noteRepository;

    public void create(Note note) {
        noteRepository.create(note);
    }

    public Note update(Note note) {
        return noteRepository.update(note);
    }

    public void remove(Long id) {
        noteRepository.remove(noteRepository.findById(id));
    }

    public Note findById(Long id) {
        return noteRepository.findById(id);
    }

    public List<Note> findAll() {
    	return noteRepository.findAllByJpql();
    }
}
