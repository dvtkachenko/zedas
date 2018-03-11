/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zedas.dtkachenko.repository;

import com.zedas.dtkachenko.model.Note;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Dependent
public class NoteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Note findById(Long id) {
        return entityManager.find(Note.class, id);
    }

    public void create(Note note) {
        entityManager.persist(note);
    }

    public Note update(Note note) {
        return entityManager.merge(note);
    }

    public void remove(Note note) {
        entityManager.remove(note);
    }

    public List<Note> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Note> criteria = cb.createQuery(Note.class);
        Root<Note> note = criteria.from(Note.class);
        criteria.select(note).orderBy(cb.asc(note.get("id")));
        return entityManager.createQuery(criteria).getResultList();
    }

    public List<Note> findAllByJpql() {
        return entityManager.createNamedQuery("Note.findAll", Note.class).getResultList();
    }
}
