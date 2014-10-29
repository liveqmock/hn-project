/**
 * 
 */
package hn.travel.persist.service.ticket;

import hn.travel.persist.entity.BlobData;
import hn.travel.persist.entity.Itinerary;
import hn.travel.persist.entity.ScenicTicket;
import hn.travel.persist.entity.Ticket;
import hn.travel.persist.repository.BlobDataDao;
import hn.travel.persist.repository.ItineraryDao;
import hn.travel.persist.repository.ScenicDao;
import hn.travel.persist.repository.ScenicTicketDao;
import hn.travel.persist.repository.TicketDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author XFZP
 * @date 2014年10月14日
 */
@Component
@Transactional(readOnly = true)
public class TicketService {

	@Autowired
	private ScenicTicketDao stDao;
	@Autowired
	private ScenicDao scenicDao;
	@Autowired
	private ItineraryDao iDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private BlobDataDao blobDataDao;
	@Autowired
	private TicketKindService ticketKindSrv;

	public Ticket getSimpleTicket(Long id) {
		Ticket ticket = new Ticket();
		ticket.setId(id);
		ScenicTicket st = stDao.findOne(id);
		if (st != null) {
			st.setScenic(scenicDao.findOne(st.getScenicId()));
			ticket.setScenicTicket(st);
		} else {
			Itinerary it = iDao.findOne(id);
			if (it != null)
				ticket.setItinerary(it);
			else
				return null;
		}
		return ticket;
	}

	@Transactional
	public Ticket save(Ticket ticket) {
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

		return ticketDao.save(ticket);
	}

	@Transactional
	public void delete(Iterable<Long> ids) {
		if (ids == null)
			return;

		Iterable<Ticket> tickets = ticketDao.findAll(ids);
		deleteAll(tickets);
	}

	@Transactional
	public void deleteAll(Iterable<? extends Ticket> tickets) {
		List<BlobData> blobDatas = new LinkedList<BlobData>();
		List<Long> ticketIds = new ArrayList<Long>();
		for (Ticket t : tickets) {
			if (t.getCost() != null)
				blobDatas.add(t.getCost());
			if (t.getNotice() != null)
				blobDatas.add(t.getNotice());
			if (t.getRefund() != null)
				blobDatas.add(t.getRefund());
			ticketIds.add(t.getId());
		}
		ticketKindSrv.deleteByTicketId(ticketIds.toArray(new Long[ticketIds
				.size()]));

		ticketDao.delete(tickets);

		blobDataDao.delete(blobDatas);
	}
}
