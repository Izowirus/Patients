package osm.web.pacienci;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import osm.web.pacienci.storage.StorageProperties;
import osm.web.pacienci.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PacieciApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacieciApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipart = new CommonsMultipartResolver();
		multipart.setMaxUploadSize(3 * 1024 * 1024);
		return multipart;
	}

	@Bean
	@Order(0)
	public MultipartFilter multipartFilter() {
		MultipartFilter multipartFilter = new MultipartFilter();
		multipartFilter.setMultipartResolverBeanName("multipartReso‌‌​​lver");
		return multipartFilter;
	}
}
