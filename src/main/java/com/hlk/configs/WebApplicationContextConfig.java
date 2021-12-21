/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hlk.formatter.AppointmentFormatter;
import com.hlk.formatter.DoctorFormatter;
import com.hlk.formatter.MedicalRecordFormatter;
import com.hlk.formatter.MedicineFormatter;
import com.hlk.formatter.NurseFormatter;
import com.hlk.formatter.OrderFormatter;
import com.hlk.formatter.PatientFormatter;
import com.hlk.formatter.PrescriptionDetailFormatter;
import com.hlk.formatter.PrescriptionFormatter;
import com.hlk.formatter.ScheduleDoctorFormatter;
import com.hlk.formatter.ScheduleNurseFormatter;
import com.hlk.formatter.SickFormatter;
import com.hlk.formatter.UserFormatter;
import com.hlk.validator.MedicineValidator;
import com.hlk.validator.WebAppValidator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author MSI GE66
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.hlk.controllers",
    "com.hlk.repository",
    "com.hlk.service",
    "com.hlk.validator",
    "com.hlk.utils"
})
public class WebApplicationContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver
                = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/layout/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
        // Set the base name for the messages properties file. 
        ret.setBasenames("classpath:config/messages", "classpath:config/admin");
        ret.setCacheSeconds(1);
        ret.setUseCodeAsDefaultMessage(true);
        ret.setDefaultEncoding("UTF-8");
        return ret;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/fonts/");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean(name = "localeInterceptor")
    public LocaleChangeInterceptor getLocaleInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Bean(name = "localeResolver")
    public CookieLocaleResolver getCookieLocaleResolver() {
        // Create a CookieLocaleResolver object.
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        // Set cookie name
        localeResolver.setCookieName("cookie-locale-info");
        // Set default locale value.
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        // Set cookie max exist time.
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLocaleInterceptor());
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(getMessageSource());

        return bean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new SickFormatter());
        registry.addFormatter(new AppointmentFormatter());
        registry.addFormatter(new DoctorFormatter());
        registry.addFormatter(new MedicalRecordFormatter());
        registry.addFormatter(new MedicineFormatter());
        registry.addFormatter(new NurseFormatter());
        registry.addFormatter(new OrderFormatter());
        registry.addFormatter(new PatientFormatter());
        registry.addFormatter(new PrescriptionDetailFormatter());
        registry.addFormatter(new PrescriptionFormatter());
        registry.addFormatter(new ScheduleDoctorFormatter());
        registry.addFormatter(new ScheduleNurseFormatter());
        registry.addFormatter(new UserFormatter());
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "linhkhoi",
                        "api_key", "528176113873651",
                        "api_secret", "tB11NGtTeSS5pBzdZFXgTRH19OA",
                        "secure", true
                ));
        
        return cloudinary;

    }
    
    @Bean
    public WebAppValidator medicineValidator(){
        Set<Validator> springValidator = new HashSet<>();
        springValidator.add(new MedicineValidator());
        
        
        WebAppValidator v = new WebAppValidator();
        v.setSpringValidators(springValidator);
        
        return v;
    }
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
 
        mailSender.setUsername("tranminh324879@gmail.com");
        mailSender.setPassword("linhkhoi18");
 
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
 
        return mailSender;
    }
}
