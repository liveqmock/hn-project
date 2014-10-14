/**
 * 
 */
package hn.travel.persist.service.ticket;

import hn.travel.persist.entity.DatePrice;
import hn.travel.persist.repository.DatePriceDao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void delete(Long ids) {
		List<Long> idList = Arrays.asList(ids);
		Iterable<DatePrice> tks = dpDao.findAll(idList);

		delete(tks);
	}

	@Transactional
	public void deleteByKindId(Long... kindId) {
		Iterable<DatePrice> tks = dpDao.findByKindIdIn(kindId);

		delete(tks);
	}

	@Transactional
	public void delete(Iterable<? extends DatePrice> datePrices) {
		dpDao.delete(datePrices);
	}
}
