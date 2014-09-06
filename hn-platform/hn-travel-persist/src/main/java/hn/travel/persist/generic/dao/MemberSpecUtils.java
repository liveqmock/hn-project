/**   
 * Copyright (c) 2014, 启创数据 All rights reserved
 */
package hn.travel.persist.generic.dao;

import hn.travel.persist.entity.member.Member;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

/** 
 * @Description 
 * @author YangYang
 * @date 2014-8-24
 * @version V1.0.0
 */

public class MemberSpecUtils {

	public static Specification<Member> getMemberSpec(Member member) {
		final Long moblie = member.getMobile();
		final String email = member.getEmail();
		final String userName = member.getUserName();
		
		final Date start = member.getStartTime();
		final Date end = member.getEndTime();
		
		return new Specification<Member>() {
			public Predicate toPredicate(Root<Member> _member,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<Member> userEntityType = _member.getModel();

				if (StringUtils.hasText(userName)) {
					Predicate userNamePredicate = builder.like(_member
							.get(userEntityType.getSingularAttribute("userName",
									String.class)), "%" + userName + "%");
					predicateList.add(userNamePredicate);
				}
				
				if (StringUtils.hasText(email)) {
					Predicate emailPredicate = builder.like(_member
							.get(userEntityType.getSingularAttribute(
									"email", String.class)), "%" + email
							+ "%");
					predicateList.add(emailPredicate);
				}
				
				if (moblie != null && moblie.compareTo(0L) > 0) {
					Predicate mobilePredicate = builder.equal(_member
							.get(userEntityType.getSingularAttribute("mobile",
									Long.class)), moblie);
					predicateList.add(mobilePredicate);
				}
				
				if (start != null && end != null) {
					Expression<Date> issueStart = builder.literal(start);
					Expression<Date> issueEnd = builder.literal(end);
					Predicate timePredicate = builder.between(_member
							.get(userEntityType.getSingularAttribute(
									"createTime", Date.class)), issueStart,
							issueEnd);
					predicateList.add(timePredicate);
				}
				// 公共
				Predicate[] predicates = new Predicate[predicateList.size()];
				predicateList.toArray(predicates);

				CriteriaQuery<?> cqy = query.where(predicates);
				// Selection<?> selectionList = cqy.getSelection();
				pc = cqy.getGroupRestriction();
				return pc;
			}
		};
	}
}
