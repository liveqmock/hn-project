package hn.travel.persist.repository;


import hn.travel.persist.entity.comment.Comment;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentDao extends PagingAndSortingRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
	
}
