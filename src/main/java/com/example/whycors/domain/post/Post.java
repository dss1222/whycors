package com.example.whycors.domain.post;

import com.example.whycors.domain.Timestamped;
import com.example.whycors.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column(nullable = false)
    private Boolean lock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Post(String title, String content, Member member, Boolean lock) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.lock = lock;
    }

    public void updatePost(String content) {
        this.content = content;
    }
}
