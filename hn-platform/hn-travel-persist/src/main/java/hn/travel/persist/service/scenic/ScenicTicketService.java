/**
 * 
 */
package hn.travel.persist.service.scenic;

import hn.travel.persist.entity.BlobData;
import hn.travel.persist.entity.ScenicTicket;
import hn.travel.persist.entity.Ticket;
import hn.travel.persist.repository.BlobDataDao;
import hn.travel.persist.repository.ScenicTicketDao;
import hn.travel.persist.repository.TicketDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author XFZP
 * @date 2014年10月12日
 */
@Component
@Transactional(readOnly = true)
public class ScenicTicketService {

	@Autowired
	private ScenicTicketDao stDao;
	@Autowired
	private BlobDataDao blobDataDao;
	@Autowired
	private TicketDao ticketDao;

	public Page<ScenicTicket> page(Long scenicId, Pageable pageable) {
		return stDao.findByScenicId(scenicId, pageable);
	}

	public ScenicTicket getDetail(Long id) {
		ScenicTicket st = stDao.findOne(id);
		if (st != null) {
			if (st.getAgreementId() != null) {
				BlobData bd = blobDataDao.findOne(st.getAgreementId());
				if (bd != null)
					st.setAgreement(bd.getData());
			}
			st.setTicket(ticketDao.findOne(id));
		}
		return st;
	}

	@Transactional
	public ScenicTicket save(ScenicTicket st) {
		Ticket ticket = st.getTicket();
		if (ticket.getCreateTime() == null)
			ticket.setCreateTime(new Date());
		ticket.setUpdateTime(new Date());

		if (ticket.getCost() != null) {
			BlobData bd = ticket.getCost();
			ticket.setCost(null);
			if (StringUtils.hasLength(bd.getData())) {
				bd = blobDataDao.save(bd);
				ticket.setCost(bd);
			} else if (bd.getId() != null) {
				blobDataDao.delete(bd.getId());
			}
		}
		if (ticket.getNotice() != null) {
			BlobData bd = ticket.getNotice();
			ticket.setNotice(null);
			if (StringUtils.hasLength(bd.getData())) {
				bd = blobDataDao.save(bd);
				ticket.setNotice(bd);
			} else if (bd.getId() != null) {
				blobDataDao.delete(bd.getId());
			}
		}
		if (ticket.getRefund() != null) {
			BlobData bd = ticket.getRefund();
			ticket.setRefund(null);
			if (StringUtils.hasLength(bd.getData())) {
				bd = blobDataDao.save(bd);
				ticket.setRefund(bd);
			} else if (bd.getId() != null) {
				blobDataDao.delete(bd.getId());
			}
		}

		ticket = ticketDao.save(ticket);
		st.setId(ticket.getId());
		st.setTicket(ticket);

		if (StringUtils.hasLength(st.getAgreement())) {
			BlobData bd = blobDataDao.save(new BlobData(st.getAgreementId(), st
					.getAgreement()));
			st.setAgreementId(bd.getId());
		} else if (st.getAgreementId() != null) {
			blobDataDao.delete(st.getAgreementId());
			st.setAgreementId(null);
		}

		stDao.save(st);
		return st;
	}

	@Transactional
	public void delete(Long... ids) {
		List<Long> idList = Arrays.asList(ids);

		Iterable<ScenicTicket> scenicTickets = stDao.findAll(idList);
		delete(scenicTickets, stDao, blobDataDao, ticketDao);
	}

	static void delete(Iterable<? extends ScenicTicket> scenicTickets,
			ScenicTicketDao stDao, BlobDataDao blobDataDao, TicketDao ticketDao) {
		List<Long> idList = new ArrayList<Long>();
		for (ScenicTicket st : scenicTickets) {
			if (st.getAgreementId() != null)
				blobDataDao.delete(st.getAgreementId());
			idList.add(st.getId());
		}
		stDao.delete(scenicTickets);

		Iterable<Ticket> tickets = ticketDao.findAll(idList);
		ticketDao.delete(tickets);

		for (Ticket t : tickets) {
			if (t.getCost() != null)
				blobDataDao.delete(t.getCost());
			if (t.getNotice() != null)
				blobDataDao.delete(t.getNotice());
			if (t.getRefund() != null)
				blobDataDao.delete(t.getRefund());
		}
	}
}
