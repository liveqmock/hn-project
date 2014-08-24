/**   
 * Copyright (c) 2014, 启创数据 All rights reserved
 */
package hn.travel.persist.generic.dao;

import hn.travel.persist.entity.User;

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
 * @author chenbing
 * @date 2014-8-17
 * @version V1.0.0
 */

public class AccSpecUtils {

	public static Specification<User> getUserSpec(User user) {
		final String loginName = user.getLoginName();
		final String name = user.getName();
		final String roles = user.getRoles();
		final Date start = user.getStartTime();
		final Date end = user.getEndTime();
		return new Specification<User>() {
			public Predicate toPredicate(Root<User> _user,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<User> userEntityType = _user.getModel();

				if (StringUtils.hasText(loginName)) {
					Predicate accountPredicate = builder.like(_user
							.get(userEntityType.getSingularAttribute("loginName",
									String.class)), "%" + loginName + "%");
					predicateList.add(accountPredicate);
				}
				if (StringUtils.hasText(name)) {
					Predicate nicknamePredicate = builder.like(_user
							.get(userEntityType.getSingularAttribute(
									"name", String.class)), "%" + name
							+ "%");
					predicateList.add(nicknamePredicate);
				}
				if (StringUtils.hasText(roles)) {
					Predicate mobilePredicate = builder.like(_user
							.get(userEntityType.getSingularAttribute("roles",
									String.class)), "%" + roles + "%");
					predicateList.add(mobilePredicate);
				}
				if (start != null && end != null) {
					Expression<Date> issueStart = builder.literal(start);
					Expression<Date> issueEnd = builder.literal(end);
					Predicate timePredicate = builder.between(_user
							.get(userEntityType.getSingularAttribute(
									"registerDate", Date.class)), issueStart,
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
