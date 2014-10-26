/**
 * 
 */
package hn.travel.persist.service.ticket;

import hn.travel.persist.entity.TicketKind;
import hn.travel.persist.repository.TicketKindDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author XFZP
 * @date 2014年10月13日
 */
@Component
@Transactional(readOnly = true)
public class TicketKindService {
	@Autowired
	private TicketKindDao tkDao;
	@Autowired
	private DatePriceService datePriceSrv;
	@Autowired
	private TicketService tSrv;

	public Page<TicketKind> page(Long ticketId, Pageable pageable) {
		return tkDao.findByTicketId(ticketId, pageable);
	}

	public TicketKind get(Long id) {
		return tkDao.findOne(id);
	}

	public TicketKind getDetail(Long id) {
		TicketKind tk = get(id);
		if (tk != null) {
			tk.setTicket(tSrv.getSimpleTicket(tk.getTicketId()));
		}
		return tk;
	}

	@Transactional
	public TicketKind save(TicketKind ticketKind) {
		if (ticketKind.getCreateTime() == null)
			ticketKind.setCreateTime(new Date());

		ticketKind.setUpdateTime(new Date());
		return tkDao.save(ticketKind);
	}

	@Transactional
	public void delete(Long... ids) {
		List<Long> idList = Arrays.asList(ids);
		Iterable<TicketKind> tks = tkDao.findAll(idList);

		delete(tks, ids);
	}

	@Transactional
	public void deleteByTicketId(Long... ticketId) {
		if (ticketId == null || ticketId.length == 0)
			return;

		List<TicketKind> tks = tkDao.findByTicketIdIn(ticketId);
		List<Long> idList = new ArrayList<Long>();
		for (TicketKind tk : tks) {
			idList.add(tk.getId());
		}

		delete(tks, idList.toArray(new Long[idList.size()]));
	}

	private void delete(Iterable<? extends TicketKind> ticketKinds,
			Long[] kindIds) {
		datePriceSrv.deleteByKindId(kindIds);

		tkDao.delete(ticketKinds);
	}
}
