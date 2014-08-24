package hn.travel.persist.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static void delFileViaPath(String rootPath, String paths) {
		if(StringUtils.hasText(paths)) {
			String[] ps = paths.split(";");
			if(ps.length >= 1) {
				for(String p : ps) {
					String path = StringTools.joinWithSeparator(new String[]{rootPath, p});
					File f = new File(path);
					if(f.exists()) {
						f.delete();
					}
				}
			}
		}
	}
	
	public static void delFile(String file) {
		if(StringUtils.hasText(file)) {
			File f = new File(file);
			if(f.exists()) {
				f.delete();
			}
		}
	}
	
	/**
	 * 复制上传文件到目录
	 * @param file
	 * @return
	 */
	public static String copyFile(MultipartFile file){
		String rootPath = PropertiesUtil.getProp("common.filePath");
		String orignSavePath=StringTools.getSavedFileNameViaDate(file.getOriginalFilename(),"");
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(
					rootPath+orignSavePath)));
			return orignSavePath;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除申请的上传文件
	 * @param fileUrl
	 */
	public static void deleteApnFile(String fileUrl){
		String rootPath = PropertiesUtil.getProp("common.filePath");
		deleteFile(rootPath+fileUrl);
		
	}
	
	public static void deleteFile(String filePath){
		File file=new File(filePath);
		if(file.exists()&&file.canWrite())
		{
			file.delete();
		}
	}
}
