/**
 * 
 */
package hn.travel.cms.web.ticket;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.Ticket;
import hn.travel.persist.entity.TicketKind;
import hn.travel.persist.service.ticket.TicketKindService;
import hn.travel.persist.service.ticket.TicketService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ecside.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author XFZP
 * @date 2014年10月13日
 */
@Controller
@RequestMapping(value = "/ticketkind")
public class TicketKindController extends GenericController {

	@Autowired
	private TicketService tSrv;
	@Autowired
	private TicketKindService tkSrv;

	@RequestMapping(value = "{ticketId}")
	public String list(@PathVariable("ticketId") Long ticketId,
			HttpServletRequest request, Model model) {
		Ticket ticket = tSrv.getSimpleTicket(ticketId);
		if (ticket == null)
			return "ticketkind/noticket";

		model.addAttribute("ticket", ticket);

		int pageNo = RequestUtil.getPageNo(request) - 1;
		pageNo = pageNo < 0 ? 0 : pageNo;
		int size = RequestUtil.getCurrentRowsDisplayed(request);
		size = size < 1 ? 15 : size;
		Page<TicketKind> page = tkSrv.page(ticketId, new PageRequest(pageNo,
				size));

		model.addAttribute("list", page.getContent());
		model.addAttribute("totalRows", Long.valueOf(page.getTotalElements())
				.intValue());
		return "ticketkind/list";
	}

	@RequestMapping(value = "create/{ticketId}", method = RequestMethod.GET)
	public String createForm(@PathVariable("ticketId") Long ticketId,
			Model model) {
		Ticket ticket = tSrv.getSimpleTicket(ticketId);
		if (ticket == null)
			return "ticketkind/noticket";

		model.addAttribute("ticket", ticket);

		model.addAttribute("vo", new TicketKind());
		return "ticketkind/form";
	}

	@RequestMapping(value = "update/{ticketId}/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("ticketId") Long ticketId,
			@PathVariable("id") Long id, Model model) {
		Ticket ticket = tSrv.getSimpleTicket(ticketId);
		if (ticket == null)
			return "ticketkind/noticket";

		model.addAttribute("ticket", ticket);

		TicketKind tk = tkSrv.get(id);
		if (tk == null)
			tk = new TicketKind();
		model.addAttribute("vo", tk);
		return "ticketkind/form";
	}

	@RequestMapping(value = "save/{ticketId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, ?> save(@PathVariable("ticketId") Long ticketId,
			@Valid TicketKind tk, HttpServletRequest request) {
		Map<String, Object> re = new HashMap<String, Object>();

		tk.setTicketId(ticketId);
		tk = tkSrv.save(tk);

		re.put("success", true);
		return re;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, ?> delete(@RequestParam Long[] ids) {
		Map<String, Object> re = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			re.put("error", "请至少选择一个门票");
			return re;
		}
		tkSrv.delete(ids);
		re.put("success", true);
		return re;
	}
}
