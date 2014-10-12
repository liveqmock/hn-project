/**
 * 
 */
package hn.travel.cms.web.scenic;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.Scenic;
import hn.travel.persist.entity.ScenicTicket;
import hn.travel.persist.service.scenic.ScenicService;
import hn.travel.persist.service.scenic.ScenicTicketService;

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
 * @date 2014年10月12日
 */
@Controller
@RequestMapping(value = "/scenicticket")
public class ScenicTicketController extends GenericController {

	@Autowired
	private ScenicTicketService stSrv;
	@Autowired
	private ScenicService sSrv;

	@RequestMapping(value = "{scenicId}")
	public String list(@PathVariable("scenicId") Long scenicId,
			HttpServletRequest request, Model model) {
		Scenic scenic = sSrv.getSimple(scenicId);
		if (scenic == null)
			return "scenicticket/noscenic";

		model.addAttribute("scenic", scenic);

		int pageNo = RequestUtil.getPageNo(request) - 1;
		pageNo = pageNo < 0 ? 0 : pageNo;
		int size = RequestUtil.getCurrentRowsDisplayed(request);
		size = size < 1 ? 15 : size;
		Page<ScenicTicket> page = stSrv.page(scenicId, new PageRequest(pageNo,
				size));

		model.addAttribute("list", page.getContent());
		model.addAttribute("totalRows", Long.valueOf(page.getTotalElements())
				.intValue());
		return "scenicticket/list";
	}

	@RequestMapping(value = "create/{scenicId}", method = RequestMethod.GET)
	public String createForm(@PathVariable("scenicId") Long scenicId,
			Model model) {
		Scenic scenic = sSrv.getSimple(scenicId);
		if (scenic == null)
			return "scenicticket/noscenic";

		model.addAttribute("scenic", scenic);

		model.addAttribute("vo", new ScenicTicket());
		return "scenicticket/form";
	}

	@RequestMapping(value = "update/{scenicId}/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("scenicId") Long scenicId,
			@PathVariable("id") Long id, Model model) {
		Scenic scenic = sSrv.getSimple(scenicId);
		if (scenic == null)
			return "scenicticket/noscenic";

		model.addAttribute("scenic", scenic);

		ScenicTicket st = stSrv.getDetail(id);
		if (st == null)
			st = new ScenicTicket();
		model.addAttribute("vo", st);
		return "scenicticket/form";
	}

	@RequestMapping(value = "save/{scenicId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, ?> save(@PathVariable("scenicId") Long scenicId,
			@Valid ScenicTicket st, HttpServletRequest request) {
		Map<String, Object> re = new HashMap<String, Object>();

		st.setScenicId(scenicId);
		st = stSrv.save(st);

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
		stSrv.delete(ids);
		re.put("success", true);
		return re;
	}
}
