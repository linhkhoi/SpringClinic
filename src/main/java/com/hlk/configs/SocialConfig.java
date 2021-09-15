///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.hlk.configs;
//
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.social.UserIdSource;
//import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
//import org.springframework.social.config.annotation.EnableSocial;
//import org.springframework.social.config.annotation.SocialConfigurer;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.UsersConnectionRepository;
//import org.springframework.social.google.connect.GoogleConnectionFactory;
//import org.springframework.social.security.AuthenticationNameUserIdSource;
//
///**
// *
// * @author MSIGE66
// */
//@Configuration
//@EnableSocial
//// Load to Environment.
//@PropertySource("classpath:social-cfg.properties")
//public class SocialConfig implements SocialConfigurer{
//    
//    private boolean autoSignUp = false;
// 
//  @Autowired
//  private DataSource dataSource;
//
//    @Override
//    public void addConnectionFactories(ConnectionFactoryConfigurer cfc, Environment e) {
//              GoogleConnectionFactory gfactory = new GoogleConnectionFactory(//
//              e.getProperty("google.client.id"), //
//              e.getProperty("google.client.secret"));
// 
//      gfactory.setScope(e.getProperty("google.scope"));
//      
//      cfc.addConnectionFactory(gfactory);
//    }
//
//    @Override
//    public UserIdSource getUserIdSource() {
//        return new AuthenticationNameUserIdSource();
//    }
//
//    @Override
//    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator cfl) {
//        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
//              connectionFactoryLocator,
// 
//              Encryptors.noOpText());
//    }
//    
//}
