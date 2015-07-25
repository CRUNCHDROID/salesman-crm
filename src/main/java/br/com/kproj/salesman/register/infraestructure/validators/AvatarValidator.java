package br.com.kproj.salesman.register.infraestructure.validators;

import static br.com.kproj.salesman.infrastructure.helpers.MultipartFileUtils.safe;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Sets;

@Component
public class AvatarValidator implements Validator, InitializingBean {

	private static final Integer MAX_SIZE = 1024;
	private final Set<String> mimeTypes = Sets.newHashSet("image/png", "image/jpeg", "image/gif");
    

    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	MultipartFile file = (MultipartFile) target;
    	
    	try {
			byte[] bytes = safe(file).getBytes();
			
			if (bytes == null) {
				return;
			}
			
			long size = safe(file).getSize();
			if (size > MAX_SIZE) {
				errors.reject("product", "product.avatar.invalid.size");
			}
			
			String contentType = safe(file).getContentType();
			
			if (!mimeTypes.contains(contentType)) {
				errors.reject("product", "product.avatar.invalid.mimetype");
			}
					
		} catch (IOException e) {
			errors.reject("product.avatar","product.avatar.is.invalid");
		}
    	
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
}