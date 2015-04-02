package com.goodfood.init;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.context.TilesRequestContextHolder;
import org.apache.tiles.definition.UnresolvingLocaleDefinitionsFactory;
import org.apache.tiles.request.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan("com.goodfood.controller")
public class WebAppConfig  extends WebMvcConfigurerAdapter{

	private static final Map<String, Definition> tiles = new HashMap<String,Definition>();
	private static final Attribute TEMPLATE = new Attribute("/WEB-INF/views/layout/layout.jsp");

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitionsFactoryClass(JavaDefinitionsFactory.class);
		tilesConfigurer.setDefinitions(new String[] {});

		addDefinition("home", "Home", "/WEB-INF/views/home.jsp");
		addDefinition("signin", "Sign in", "/WEB-INF/views/signin.jsp");
		addDefinition("signin/facebook", "Sign in", "/WEB-INF/views/signin.jsp");

		return tilesConfigurer;
	}

	private void addDefinition(String name, String title, String body) {
		Map<String, Attribute> attributes = getDefaultAttributes();

		attributes.put("title", new Attribute(title));
		attributes.put("body", new Attribute(body));

		tiles.put(name, new Definition(name, TEMPLATE, attributes));
	}

	private Map<String, Attribute> getDefaultAttributes() {
		Map<String, Attribute> attributes = new HashMap<String,Attribute>();

		attributes.put("header", new Attribute("/WEB-INF/views/layout/header.jsp"));
		attributes.put("menu", new Attribute("/WEB-INF/views/layout/menu.jsp"));
		attributes.put("footer", new Attribute("/WEB-INF/views/layout/footer.jsp"));

		return attributes;
	}

	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	public static class JavaDefinitionsFactory extends UnresolvingLocaleDefinitionsFactory {

		public JavaDefinitionsFactory(){}

		@Override
		public Definition getDefinition(String name, Request tilesContext) {
			return tiles.get(name);
		}
	}

	// spring message config bean
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("i18n/messages");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver=new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(10000000);
		return resolver;
	}

	/**
	 * {@inheritDoc}
	 * <p>This implementation is empty.
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// Only needed if we are using @Value and ${...} when referencing properties
	// Otherwise @PropertySource is enough by itself
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySources = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] {
				new ClassPathResource("application.properties") };
		propertySources.setLocations(resources);
		propertySources.setIgnoreUnresolvablePlaceholders(true);
		return propertySources;
	}
}
