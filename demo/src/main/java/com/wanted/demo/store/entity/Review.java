package com.wanted.demo.store.entity;

import com.wanted.demo.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;

    private String evaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Review(int rating, String evaluation, User user, Store store) {
        this.rating = rating;
        this.evaluation = evaluation;
        this.user = user;
        this.store = store;
    }

    public void reviewer(User user) {
        this.user = user;
        if (!user.getReviews().contains(this)) {
            user.write(this);
        }
    }

    public void about(Store store) {
        this.store = store;
        if (!store.getReviews().contains(this)) {
            store.receives(this);
        }
    }
}
