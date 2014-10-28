/**
 * 
 */
package hn.travel.cms.web.hotel;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.Hotel;
import hn.travel.persist.entity.HotelRoom;
import hn.travel.persist.service.hotel.HotelRoomService;
import hn.travel.persist.service.hotel.HotelService;

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
 * @date 2014年10月26日
 */
@Controller
@RequestMapping(value = "/hotelroom")
public class HotelRoomController extends GenericController {

	@Autowired
	private HotelRoomService srv;
	@Autowired
	private HotelService hotelSrv;

	@RequestMapping(value = "{hotelId}")
	public String list(@PathVariable("hotelId") Long hotelId,
			HttpServletRequest request, Model model) {
		Hotel hotel = hotelSrv.get(hotelId);
		if (hotel == null)
			return "hotelroom/nohotel";

		model.addAttribute("hotel", hotel);

		int pageNo = RequestUtil.getPageNo(request) - 1;
		pageNo = pageNo < 0 ? 0 : pageNo;
		int size = RequestUtil.getCurrentRowsDisplayed(request);
		size = size < 1 ? 15 : size;
		Page<HotelRoom> page = srv.page(hotelId, new PageRequest(pageNo, size));

		model.addAttribute("list", page.getContent());
		model.addAttribute("totalRows", Long.valueOf(page.getTotalElements())
				.intValue());
		return "hotelroom/list";
	}

	@RequestMapping(value = "create/{hotelId}", method = RequestMethod.GET)
	public String createForm(@PathVariable("hotelId") Long hotelId, Model model) {
		Hotel hotel = hotelSrv.get(hotelId);
		if (hotel == null)
			return "hotelroom/nohotel";

		model.addAttribute("hotel", hotel);

		model.addAttribute("vo", new HotelRoom());
		return "hotelroom/form";
	}

	@RequestMapping(value = "update/{hotelId}/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("hotelId") Long hotelId,
			@PathVariable("id") Long id, Model model) {
		Hotel hotel = hotelSrv.get(hotelId);
		if (hotel == null)
			return "hotelroom/nohotel";

		model.addAttribute("hotel", hotel);

		HotelRoom hr = srv.getDetail(id);
		if (hr == null)
			hr = new HotelRoom();
		model.addAttribute("vo", hr);
		return "hotelroom/form";
	}

	@RequestMapping(value = "save/{hotelId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, ?> save(@PathVariable("hotelId") Long hotelId,
			@Valid HotelRoom hr, HttpServletRequest request) {
		Map<String, Object> re = new HashMap<String, Object>();

		hr.setHotelId(hotelId);
		hr = srv.save(hr);

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
		srv.delete(ids);
		re.put("success", true);
		return re;
	}

}
