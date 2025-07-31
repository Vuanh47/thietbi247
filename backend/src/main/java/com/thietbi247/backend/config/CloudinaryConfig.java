package com.thietbi247.backend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "Root",
                "api_key", "569945768266727",
                "api_secret", "qk027W8f0hOI3IDTd2E3TiaxCEM",
                "secure", true
        ));
    }
}