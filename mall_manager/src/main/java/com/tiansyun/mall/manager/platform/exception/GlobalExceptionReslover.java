package com.tiansyun.mall.manager.platform.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理 日志等部分待完善
 * @author ZYJ
 *
 */
@Component
public class GlobalExceptionReslover implements HandlerExceptionResolver {
	
	//slf4j对象
	Logger logger = LoggerFactory.getLogger(GlobalExceptionReslover.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		//1,将异常打印到控制台
		e.printStackTrace();
		//2,打印日志
		//logger.error("系统发生异常", e);
		//3,发送邮件或者短信
		//4,返回错误视图
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("系统发生异常",e);
		modelAndView.setViewName("/error");
		return modelAndView;
	}

}
