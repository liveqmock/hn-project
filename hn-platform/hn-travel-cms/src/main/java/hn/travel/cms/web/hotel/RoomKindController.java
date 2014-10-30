/**
 * 
 */
package hn.travel.cms.web.hotel;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.HotelRoom;
import hn.travel.persist.entity.RoomKind;
import hn.travel.persist.service.hotel.HotelRoomService;
import hn.travel.persist.service.hotel.RoomKindService;

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
 * @date 2014年10月30日
 */
@Controller
@RequestMapping(value = "/roomkind")
public class RoomKindController extends GenericController {

	@Autowired
	private RoomKindService srv;
	@Autowired
	private HotelRoomService hotelRoomSrv;

	@RequestMapping(value = "{roomId}")
	public String list(@PathVariable("roomId") Long roomId,
			HttpServletRequest request, Model model) {
		HotelRoom hotelRoom = hotelRoomSrv.getWithHotel(roomId);
		if (hotelRoom == null)
			return "roomkind/noroom";

		model.addAttribute("room", hotelRoom);

		int pageNo = RequestUtil.getPageNo(request) - 1;
		pageNo = pageNo < 0 ? 0 : pageNo;
		int size = RequestUtil.getCurrentRowsDisplayed(request);
		size = size < 1 ? 15 : size;
		Page<RoomKind> page = srv.page(roomId, new PageRequest(pageNo, size));

		model.addAttribute("list", page.getContent());
		model.addAttribute("totalRows", Long.valueOf(page.getTotalElements())
				.intValue());
		return "roomkind/list";
	}

	@RequestMapping(value = "create/{roomId}", method = RequestMethod.GET)
	public String createForm(@PathVariable("roomId") Long roomId, Model model) {
		HotelRoom hotelRoom = hotelRoomSrv.getWithHotel(roomId);
		if (hotelRoom == null)
			return "roomkind/noroom";

		model.addAttribute("room", hotelRoom);

		model.addAttribute("vo", new RoomKind());
		return "roomkind/form";
	}

	@RequestMapping(value = "update/{roomId}/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("roomId") Long roomId,
			@PathVariable("id") Long id, Model model) {
		HotelRoom hotelRoom = hotelRoomSrv.getWithHotel(roomId);
		if (hotelRoom == null)
			return "roomkind/noroom";

		model.addAttribute("room", hotelRoom);

		RoomKind rk = srv.get(id);
		if (rk == null)
			rk = new RoomKind();
		model.addAttribute("vo", rk);
		return "roomkind/form";
	}

	@RequestMapping(value = "save/{roomId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, ?> save(@PathVariable("roomId") Long roomId,
			@Valid RoomKind rk, HttpServletRequest request) {
		Map<String, Object> re = new HashMap<String, Object>();

		rk.setRoomId(roomId);
		rk = srv.save(rk);

		re.put("success", true);
		return re;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, ?> delete(@RequestParam Long[] ids) {
		Map<String, Object> re = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			re.put("error", "请至少选择一个房型");
			return re;
		}
		srv.delete(ids);
		re.put("success", true);
		return re;
	}

}
