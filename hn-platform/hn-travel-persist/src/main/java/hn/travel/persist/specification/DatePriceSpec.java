/**
 * 
 */
package hn.travel.persist.specification;

import hn.travel.persist.entity.DatePrice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author XFZP
 * @date 2014年10月15日
 */
public class DatePriceSpec implements Specification<DatePrice> {
	private Long kindId;
	private Date startPdate;
	private Date endPdate;

	public DatePriceSpec() {
		super();
	}

	public DatePriceSpec(Long kindId, Date startPdate, Date endPdate) {
		super();
		this.kindId = kindId;
		this.startPdate = startPdate;
		this.endPdate = endPdate;
	}

	@Override
	public Predicate toPredicate(Root<DatePrice> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> list = new ArrayList<Predicate>(3);

		EntityType<DatePrice> entityType = root.getModel();

		if (kindId != null) {
			Path<Long> path = root.get(entityType.getSingularAttribute(
					"kindId", Long.class));
			list.add(cb.equal(path, kindId));
		}

		if (startPdate != null) {
			Path<Date> path = root.get(entityType.getSingularAttribute("pdate",
					Date.class));
			list.add(cb.greaterThanOrEqualTo(path, startPdate));
		}
		if (endPdate != null) {
			Path<Date> path = root.get(entityType.getSingularAttribute("pdate",
					Date.class));
			list.add(cb.lessThanOrEqualTo(path, endPdate));
		}

		return cb.and(list.toArray(new Predicate[list.size()]));
	}

	public Long getKindId() {
		return kindId;
	}

	public void setKindId(Long kindId) {
		this.kindId = kindId;
	}

	public Date getStartPdate() {
		return startPdate;
	}

	public void setStartPdate(Date startPdate) {
		this.startPdate = startPdate;
	}

	public Date getEndPdate() {
		return endPdate;
	}

	public void setEndPdate(Date endPdate) {
		this.endPdate = endPdate;
	}

}
