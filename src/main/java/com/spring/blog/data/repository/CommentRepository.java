package com.spring.blog.data.repository;

import com.spring.blog.data.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //TODO paging, sort
    //TODO 동적쿼리 생성 시, Specification 이라는 것을 사용 ->
    //TODO 다국어 설정 i18n 사용하지 말고 설정 해보기


}
