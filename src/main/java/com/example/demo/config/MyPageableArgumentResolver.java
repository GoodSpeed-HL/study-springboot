package com.example.demo.config;


import com.example.demo.execption.PageableInvalidPageException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


public class MyPageableArgumentResolver extends PageableHandlerMethodArgumentResolver {
    @Override
    public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws PageableInvalidPageException {
        String page = nativeWebRequest.getParameter("page");
        int parsedPage = 0;
        if(page != null){
            parsedPage = Integer.parseInt(nativeWebRequest.getParameter("page"));
        }
        //int size = Integer.parseInt(nativeWebRequest.getParameter("size"));
        if(parsedPage <= 0){
            throw new PageableInvalidPageException();
        }
        return super.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);

    }
}
