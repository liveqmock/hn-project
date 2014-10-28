/**
 * 
 */
package hn.travel.cms.web.hotel;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.Hotel;
import hn.travel.persist.service.hotel.HotelService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * @author XFZP
 * @date 2014年10月26日
 */
@Controller
@RequestMapping(value = "/hotel")
public class HotelController extends GenericController {

	@Autowired
	private HotelService srv;

	@RequestMapping
	public String list(String keyword, HttpServletRequest request, Model model) {
		int pageNo = RequestUtil.getPageNo(request) - 1;
		pageNo = pageNo < 0 ? 0 : pageNo;
		int size = RequestUtil.getCurrentRowsDisplayed(request);
		size = size < 1 ? 15 : size;
		Page<Hotel> page = srv.page(keyword, new PageRequest(pageNo, size));

		model.addAttribute("list", page.getContent());
		model.addAttribute("totalRows", Long.valueOf(page.getTotalElements())
				.intValue());
		return "hotel/list";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vo", new Hotel());
		return "hotel/form";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Hotel vo = srv.getDetail(id);
		if (vo == null)
			vo = new Hotel();
		model.addAttribute("vo", vo);
		return "hotel/form";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void save(@Valid Hotel hotel, MultipartFile imgUriFile,
			HttpServletRequest request, HttpServletResponse resp) {
		Map<String, Object> re = new HashMap<String, Object>();
		if (imgUriFile == null || imgUriFile.isEmpty()) {
			if (hotel.getId() == null) {
				re.put("error", "主图不能为空");
				writeUploadJson(resp, re);
				return;
			}
		} else {
			String saveUrl = uploadImg(re, imgUriFile, request);
			if (saveUrl == null) {
				writeUploadJson(resp, re);
				return;
			}
			hotel.setImgUri(saveUrl);
		}

		srv.save(hotel);
		re.put("success", true);
		writeUploadJson(resp, re);
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, ?> delete(@RequestParam Long[] ids) {
		Map<String, Object> re = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			re.put("error", "请至少选择一个酒店");
			return re;
		}
		srv.delete(ids);
		re.put("success", true);
		return re;
	}

}
