package com.ad4565.imagefinder;

import com.ad4565.imagefinder.model_sql.WebsiteData;
import com.ad4565.imagefinder.repository.WebsiteDataRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class ImagefinderApplication {

	public static void main(String[] args) {


		ConfigurableApplicationContext configurableApplicationContext
				= SpringApplication.run(ImagefinderApplication.class, args);
	}

}
