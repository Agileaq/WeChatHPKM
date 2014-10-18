package com.hp.cdc.km.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hp.cdc.km.entity.WechatUser;
import com.hp.cdc.km.service.IFacadeService;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhongl
 * Date: 9/5/14
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginFilter  implements  Filter{
	
	  final static Logger logger = Logger.getLogger(LoginFilter.class);
	  
	  private List<String> excludedUrlList;
	   
	  @Autowired
	  private IFacadeService service; 
	  
    public LoginFilter() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	logger.info("start in do filter");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        boolean loginSuccess=isLogin(httpServletRequest);
        String url=httpServletRequest.getRequestURL().toString();
        System.out.println(url);
        boolean freePermission=false;
        //boolean freePermission=url.endsWith("login.html") || url.endsWith("agenda-list.html") || url.endsWith("agenda.html");
        for(String exculde:excludedUrlList){
        	if(url.endsWith(exculde.trim())){
        		freePermission = true;
        		break;
        	}
        }
        if(loginSuccess|| freePermission){
           chain.doFilter(request,response);
        }else{
        	
//        	httpServletRequest.getSession().setAttribute("redirectPage", url);
//        	httpServletRequest.getSession().setAttribute("wechatUserID", );
        	String originalPage = httpServletRequest.getRequestURL()+"?wechatUserID="+httpServletRequest.getParameter("wechatUserID");
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/views/login.html?originalPage="+originalPage);
        }

    }


    public List<String> getExcludedUrlList() {
		return excludedUrlList;
	}

	public void setExcludedUrlList(List<String> excludedUrlList) {
		this.excludedUrlList = excludedUrlList;
	}

	private boolean isLogin( HttpServletRequest request) {
    	String openUserID= request.getParameter("openUserID");
    	WechatUser weChatUser= service.getWechatUser(openUserID);
    	if(weChatUser== null){
    		return false;
    	}
    	return true;
    }

}