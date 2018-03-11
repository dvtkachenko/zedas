package com.zedas.dtkachenko.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "note")
@NamedQueries({
        @NamedQuery(name = "Note.findAll",
                query = "select n from Note n order by n.id")
})
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_sequence")
    @SequenceGenerator(name = "note_sequence", sequenceName = "note_sequence")
    private Long id;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "text")
    private String text;

    public Note() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note that = (Note) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", text='" + text + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
