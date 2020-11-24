package me.belz.hellospringdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;
    private String task;
    private String tag = "Personal";
    private int priority = 5;
    private boolean done = false;

    protected Todo() { }

    public Todo(String task) {
        this.task = task;
    }

    public Todo(String task, String tag) {
        this.task = task;
        this.tag = tag;
    }

    public Todo(String task, int priority) {
        this.task = task;
        this.priority = priority;
    }

    public Todo(String task, String tag, int priority) {
        this.task = task;
        this.tag = tag;
        this.priority = priority;
        this.done = false;
    }

    public void setTask(String task) {
        this.task = task;
    }
    public String getTask() {
        return task;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getPriority() {
        return priority;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
      return String.format(
          "TODO[id=%d, tag='%s', task='%s', priority='%s', done='%s']",
          id, tag, task, priority, done);
    }

}
