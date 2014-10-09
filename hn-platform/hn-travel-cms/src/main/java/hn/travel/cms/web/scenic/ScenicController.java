/**
 * 
 */
package hn.travel.cms.web.scenic;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.Scenic;
import hn.travel.persist.service.scenic.ScenicService;
import hn.travel.persist.utils.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String list(HttpServletRequest request, Model model) {
		Page<Scenic> page = scenicService.page(new PageRequest(0, 15));
		model.addAttribute("list", page.getContent());
		model.addAttribute("totalRows", Long.valueOf(page.getTotalElements())
				.intValue());
		return "scenic/list";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm() {
		return "scenic/form";
	}

	private List<String> imgExt = Arrays.<String> asList(new String[] { "gif",
			"jpg", "jpeg", "png", "bmp" });

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Scenic scenic, MultipartFile imgUriFile,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (imgUriFile == null || imgUriFile.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "主图不能为空");
			return "redirect:/scenic/create";
		}
		String fileName = imgUriFile.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();
		if (!imgExt.contains(fileExt)) {
			redirectAttributes.addFlashAttribute("message",
					"只允许上传图片类型（gif,jpg,jpeg,png,bmp）");
			return "redirect:/scenic/create";
		}

		String realPath = PropertiesUtil.getProp("scenic.imgPath");
		scenic.setImgUri(realPath + getUuid() + "." + fileExt);
		realPath = request.getServletContext().getRealPath(scenic.getImgUri());
		try {
			imgUriFile.transferTo(new File(realPath));
		} catch (IllegalStateException | IOException e) {
			redirectAttributes.addFlashAttribute("message", "主图上传错误");
			return "redirect:/scenic/create";
		}
		String notice = getParam(request, "notice", "");
		String introduce = getParam(request, "introduce", "");
		String traffic = getParam(request, "traffic", "");

		scenic.setCreateTime(new Date());
		scenic.setUpdateTime(new Date());
		scenicService.save(scenic, notice, introduce, traffic);
		redirectAttributes.addFlashAttribute("message", "创建景点成功");
		return "redirect:/scenic/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {

		return "scenic/form";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid Scenic scenic,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("message", "更新景点成功");
		return "redirect:/scenic/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/scenic/";
	}

	private int uuidIndex = 0;

	private synchronized String getUuid() {
		long t = System.currentTimeMillis();
		return String.format("%d%04d", t, uuidIndex++);
	}
}
