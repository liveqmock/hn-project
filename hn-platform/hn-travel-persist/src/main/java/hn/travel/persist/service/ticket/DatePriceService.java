/**
 * 
 */
package hn.travel.persist.service.ticket;

import hn.travel.persist.entity.DatePrice;
import hn.travel.persist.repository.DatePriceDao;
import hn.travel.persist.specification.DatePriceSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author XFZP
 * @date 2014年10月14日
 */
@Component
@Transactional(readOnly = true)
public class DatePriceService {

	@Autowired
	private DatePriceDao dpDao;

	@Transactional
	public void delete(Long... ids) {
		List<Long> idList = Arrays.asList(ids);
		Iterable<DatePrice> tks = dpDao.findAll(idList);

		delete(tks);
	}

	@Transactional
	public void deleteByKindId(Long... kindIds) {
		if (kindIds == null || kindIds.length == 0)
			return;

		Iterable<DatePrice> tks = dpDao.findByKindIdIn(kindIds);

		delete(tks);
	}

	@Transactional
	public void delete(Iterable<? extends DatePrice> datePrices) {
		dpDao.delete(datePrices);
	}

	@Transactional
	public DatePrice save(DatePrice datePrice) {
		return dpDao.save(datePrice);
	}

	@Transactional
	public Iterable<DatePrice> save(DatePrice datePrice, Date startDate,
			Date endDate) {
		List<DatePrice> list = new ArrayList<DatePrice>();
		if (startDate == null || endDate == null) {
			list.add(save(datePrice));
			return list;
		}

		Calendar startCalendar = getCalendar(startDate);
		Calendar endCalendar = getCalendar(endDate);
		while (startCalendar.compareTo(endCalendar) <= 0) {
			DatePrice d = new DatePrice();
			BeanUtils.copyProperties(datePrice, d);
			d.setPdate(startCalendar.getTime());
			list.add(d);
			startCalendar.set(Calendar.DATE,
					startCalendar.get(Calendar.DATE) + 1);
		}
		return dpDao.save(list);
	}

	public Page<DatePrice> page(Long kindId, Date startPdate, Date endPdate,
			Pageable pageable) {
		return dpDao.findAll(new DatePriceSpec(kindId, startPdate, endPdate),
				pageable);
	}

	public DatePrice get(Long id) {
		return dpDao.findOne(id);
	}

	private Calendar getCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}
}
