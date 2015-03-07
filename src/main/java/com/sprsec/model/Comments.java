package com.sprsec.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 18.02.2015.
 */

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @Column(name = "id_comment")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idComment;

    @Column(name = "text_comment")
    private String textComment;

    @Column(name = "date_comment")
    private Date dateComment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user")
    private User user;

    // Exception
    // Why don't work this relations?
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "comment_to_food",
            joinColumns = {@JoinColumn(name = "fk_idcomment", referencedColumnName = "id_comment")},
            inverseJoinColumns = {@JoinColumn(name = "fk_food", referencedColumnName = "id_food_tc")})
    private Food food;

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food foodList) {
        this.food = foodList;
    }
}
