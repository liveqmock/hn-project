package hn.travel.persist.repository.member;

import hn.travel.persist.entity.member.Member;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberDao extends PagingAndSortingRepository<Member, Long>,
		JpaSpecificationExecutor<Member> {
	
	Member findByUserName(String userName);
	
	Member findByMobile(String mobile);
	
	Member findByEmail(String email);
	
}
