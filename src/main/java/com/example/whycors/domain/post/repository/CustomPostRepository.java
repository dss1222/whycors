package com.example.whycors.domain.post.repository;

import com.example.whycors.domain.post.Post;
import com.example.whycors.domain.post.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomPostRepository {

    @PersistenceContext EntityManager em;
    JPAQueryFactory queryFactory;

    public List<Post> postView(Long id) {

        queryFactory = new JPAQueryFactory(em);
        QPost p = new QPost("p");

        List<Post> postList = queryFactory
                .selectFrom(p)
                .where(
                        p.lock.eq(false)
                                .or(p.lock.eq(true)
                                        .and(p.member.id.eq(id)))
                )
                .fetch();


        return postList;
    }



}
