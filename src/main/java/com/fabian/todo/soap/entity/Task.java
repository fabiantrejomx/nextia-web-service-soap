package com.fabian.todo.soap.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Task {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "subject", nullable = false, length = 150)
    private String subject;

    @Column(name = "done")
    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Task(final String subject, final boolean done, final User user){
        this.subject = subject;
        this.done = done;
        this.user = user;
    }

    public void update(final String subject, final boolean done){
        this.subject = subject;
        this.done = done;
    }

}
