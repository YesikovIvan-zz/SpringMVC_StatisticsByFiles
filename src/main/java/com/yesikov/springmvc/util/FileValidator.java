package com.yesikov.springmvc.util;

import com.yesikov.springmvc.model.FileBucket;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
public class FileValidator implements Validator {
		
	public boolean supports(Class<?> clazz) {
		return FileBucket.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		FileBucket file = (FileBucket) obj;
			
		if(file.getFile()!=null){
			if (file.getFile().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
}

