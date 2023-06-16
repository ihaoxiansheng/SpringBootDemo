//package com.hao.springbootdemo.config.async;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.SpringApplicationRunListener;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.core.env.ConfigurableEnvironment;
//
//public class MyApplicationRunListener implements SpringApplicationRunListener {
//
//  private final SpringApplication application;
//  private final String[] args;
//
//  public MyApplicationRunListener(SpringApplication sa, String[] args) {
//    this.application = sa;
//    this.args = args;
//  }
//
//  @Override
//  public void starting() {
//    System.out.println("服务启动RunnerTest  SpringApplicationRunListener的starting方法...");
//  }
//
//  @Override
//  public void environmentPrepared(ConfigurableEnvironment environment) {
//    System.out.println("服务启动RunnerTest  SpringApplicationRunListener的environmentPrepared方法...");
//  }
//
//  @Override
//  public void contextPrepared(ConfigurableApplicationContext context) {
//    System.out.println("服务启动RunnerTest  SpringApplicationRunListener的contextPrepared方法...");
//  }
//
//  @Override
//  public void contextLoaded(ConfigurableApplicationContext context) {
//    System.out.println("服务启动RunnerTest  SpringApplicationRunListener的contextLoaded方法...");
//  }
//
//  @Override
//  public void running(ConfigurableApplicationContext context) {
//    System.out.println("服务启动RunnerTest  SpringApplicationRunListener的running方法...");
//  }
//
//  @Override
//  public void failed(ConfigurableApplicationContext context, Throwable exception) {
//    System.out.println("服务启动RunnerTest  SpringApplicationRunListener的failed方法...");
//  }
//
//  @Override
//  public void started(ConfigurableApplicationContext context) {
//    System.out.println("服务启动RunnerTest  SpringApplicationRunListener的started方法...");
//  }
//}
