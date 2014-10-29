/**
 * 
 */
package hn.travel.cms.web.scenic;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.Scenic;
import hn.travel.persist.service.scenic.ScenicService;

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
 * @date 2014年10月8日
 */
@Controller
@RequestMapping(value = "/scenic")
public class ScenicController extends GenericController {

	@Autowired
	private ScenicService scenicService;

	@RequestMapping
	public String list(String keyword, HttpServletRequest request, Model model) {
		int pageNo = RequestUtil.getPageNo(request) - 1;
		pageNo = pageNo < 0 ? 0 : pageNo;
		int size = RequestUtil.getCurrentRowsDisplayed(request);
		size = size < 1 ? 15 : size;
		Page<Scenic> page = scenicService.page(keyword, new PageRequest(pageNo,
				size));

		model.addAttribute("list", page.getContent());
		model.addAttribute("totalRows", Long.valueOf(page.getTotalElements())
				.intValue());
		return "scenic/list";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vo", new Scenic());
		return "scenic/form";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Scenic scenic = scenicService.get(id);
		if (scenic == null)
			scenic = new Scenic();
		model.addAttribute("vo", scenic);
		return "scenic/form";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void save(@Valid Scenic scenic, MultipartFile imgUriFile,
			HttpServletRequest request, HttpServletResponse resp) {
		Map<String, Object> re = new HashMap<String, Object>();
		if (imgUriFile == null || imgUriFile.isEmpty()) {
			if (scenic.getId() == null) {
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
			scenic.setImgUri(saveUrl);
		}

		scenicService.save(scenic);
		re.put("success", true);
		writeUploadJson(resp, re);
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, ?> delete(@RequestParam Long[] ids) {
		Map<String, Object> re = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			re.put("error", "请至少选择一个门票");
			return re;
		}
		scenicService.delete(ids);
		re.put("success", true);
		return re;
	}

}
