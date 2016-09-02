package com.loopdiner.comman;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/Common")
public class CommonController {

	public CommonController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/savePic" , method = RequestMethod.POST)
	@ResponseBody
    public BackResult savePic(@RequestParam(value = "file", required = false) MultipartFile file, 
    		HttpServletRequest request, ModelMap model) {
		System.out.println("savePic..............start.");
		BackResult backResult = new BackResult();
		backResult.setStatus("success");
		try {
			//String path = request.getSession().getServletContext().getRealPath("upload");  
			String realpath = request.getSession().getServletContext().getRealPath("/pic"); 

			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator iter = multiRequest.getFileNames();
				if (iter.hasNext()) {
					MultipartFile file1 = multiRequest.getFile((String) iter.next());
					if (file1 != null) {
						String orgFileName = file1.getOriginalFilename();
						String newFileName = orgFileName;
						if(orgFileName.indexOf(".")<0){
							 System.out.println("add .jpg .................");
							newFileName = newFileName + ".jpg";
						}
						//String path = realpath + "/" + file1.getOriginalFilename();
						String path = realpath + "/" + newFileName;
						 System.out.println("path:"+path);
						File localFile = new File(path);
						file1.transferTo(localFile);
					}
				} 
			}
			System.out.println("savePic..............success.");
		} catch (Exception e) {
			e.printStackTrace();
			backResult.setStatus("fail");
			System.out.println("savePic..............fail.");
		} 
        return backResult;
	}
}
