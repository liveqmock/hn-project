/**
 * 
 */
package hn.travel.cms.web.ticket;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.DatePrice;
import hn.travel.persist.entity.TicketKind;
import hn.travel.persist.service.ticket.DatePriceService;
import hn.travel.persist.service.ticket.TicketKindService;
import hn.travel.persist.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ecside.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author XFZP
 * @date 2014年10月15日
 */
@Controller
@RequestMapping(value = "/dateprice")
public class DatePriceController extends GenericController {

	@Autowired
	private TicketKindService ticketKindSrv;
	@Autowired
	private DatePriceService datePriceSrv;

	@RequestMapping(value = "{kindId}")
	public String list(@PathVariable("kindId") Long kindId, Date startPdate,
			Date endPdate, HttpServletRequest request, Model model) {
		TicketKind ticketKind = ticketKindSrv.getDetail(kindId);
		if (ticketKind == null)
			return "dateprice/nokind";

		model.addAttribute("ticketKind", ticketKind);
		model.addAttribute("startDate", new Date());

		int pageNo = RequestUtil.getPageNo(request) - 1;
		pageNo = pageNo < 0 ? 0 : pageNo;
		int size = RequestUtil.getCurrentRowsDisplayed(request);
		size = size < 1 ? 15 : size;
		Page<DatePrice> page = datePriceSrv.page(kindId, startPdate, endPdate,
				new PageRequest(pageNo, size, Direction.ASC, "pdate"));

		model.addAttribute("list", page.getContent());
		model.addAttribute("totalRows", Long.valueOf(page.getTotalElements())
				.intValue());
		return "dateprice/list";
	}

	@RequestMapping(value = "create/{kindId}", method = RequestMethod.GET)
	public String createForm(@PathVariable("kindId") Long kindId, Model model) {
		TicketKind ticketKind = ticketKindSrv.getDetail(kindId);
		if (ticketKind == null)
			return "dateprice/nokind";

		model.addAttribute("ticketKind", ticketKind);

		model.addAttribute("startDate", new Date());
		model.addAttribute("vo", new DatePrice());
		return "dateprice/form";
	}

	@RequestMapping(value = "update/{kindId}/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("kindId") Long kindId,
			@PathVariable("id") Long id, Model model) {
		TicketKind ticketKind = ticketKindSrv.getDetail(kindId);
		if (ticketKind == null)
			return "dateprice/nokind";

		model.addAttribute("ticketKind", ticketKind);

		DatePrice datePrice = datePriceSrv.get(id);
		if (datePrice == null)
			datePrice = new DatePrice();
		model.addAttribute("vo", datePrice);
		return "dateprice/form";
	}

	@RequestMapping(value = "save/{kindId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, ?> save(@PathVariable("kindId") Long kindId,
			@Valid DatePrice datePrice, Date startDate, Date endDate,
			HttpServletRequest request) {
		Map<String, Object> re = new HashMap<String, Object>();

		datePrice.setKindId(kindId);
		datePriceSrv.save(datePrice, startDate, endDate);

		re.put("success", true);
		return re;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, ?> delete(@RequestParam Long[] ids) {
		Map<String, Object> re = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			re.put("error", "请至少选择一个日期价格");
			return re;
		}
		datePriceSrv.delete(ids);
		re.put("success", true);
		return re;
	}

	/*
	 * 表单提交日期绑定
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = DateUtil.SHORT_FORMAT;
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}
