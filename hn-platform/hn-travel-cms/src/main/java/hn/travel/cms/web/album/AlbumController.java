package hn.travel.cms.web.album;

import hn.travel.cms.generic.web.GenericController;
import hn.travel.persist.entity.album.Album;
import hn.travel.persist.service.album.AlbumService;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ecside.util.RequestUtil;
import org.extremecomponents.table.limit.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/album")
public class AlbumController extends GenericController{
	private static final String PAGE_SIZE = "3";
	
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("createTime", "时间");
	}
	
	@Autowired
	private AlbumService albumService;
	
	
	
	@RequestMapping(value = "list")
	public String list(Album album, HttpServletRequest request, Model model) {
		model.addAttribute("album", album);
		int pageSize = getParam(request, "ec_rd", 13);
		int pageNo = getParam(request, "ec_p", 1);
		/*获取总记录数*/
		int totalRows = (int)albumService.queryCount(album);
		String tableId = RequestUtil.getTableId(request);
		Limit limit = RequestUtil.getLimit(request, tableId, totalRows, pageSize);
		limit.setRowAttributes(totalRows, pageSize);
		org.extremecomponents.table.limit.Sort sort = limit.getSort();
		String sortKey = sort.getProperty();
		String sortValue = sort.getSortOrder();
		Pageable pageable = null;
		if(StringUtils.hasText(sortKey)) {
			if("desc".equals(sortValue)) 
				pageable = new PageRequest((pageNo-1), pageSize, new Sort(Direction.DESC, sortKey));
			else 
				pageable = new PageRequest((pageNo-1), pageSize, new Sort(Direction.ASC, sortKey));
		} else {
			pageable = new PageRequest((pageNo-1), pageSize, new Sort(Direction.DESC, "createTime"));
		}
		Page<Album> page = albumService.queryList(album, pageable);
		model.addAttribute("albumList", page.getContent());
		return "album/albumList";
	}
	
	
	@RequestMapping(value = "listPage",method = RequestMethod.GET)
	public String listPage(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<Album> albumListPage = albumService.queryAlbumListPage(searchParams, pageNumber, pageSize, sortType);
		model.addAttribute("albumListPage", albumListPage);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		return "album/albumList";
	}

	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAdd(Model model) {
		model.addAttribute("album", new Album());
		model.addAttribute("action", "create");
		return "album/albumAdd";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@Valid Album album, RedirectAttributes redirectAttributes) {
		albumService.save(album);
		redirectAttributes.addFlashAttribute("message", "创建任务成功");
		return "redirect:/album/list";
	}

	@RequestMapping(value = "toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Album album=albumService.getEntity(id);
		model.addAttribute("album", album);
		model.addAttribute("action", "update");
		return "album/albumUpdate";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("album") Album album, RedirectAttributes redirectAttributes) {
		albumService.save(album);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return "redirect:/album/list?id=2";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		albumService.delete(id);
		redirectAttributes.addFlashAttribute("message", "删除任务成功");
		return "redirect:/album/list";
	}
	
	
	@RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
	public String getById(@PathVariable("id") Long id, Model model) {
		Album album=albumService.getEntity(id);
		model.addAttribute("album", album);
		return "album/album";
	}
}
