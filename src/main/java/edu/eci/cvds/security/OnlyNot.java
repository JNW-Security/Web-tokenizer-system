package edu.eci.cvds.security;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class OnlyNot extends AccessControlFilter {
    String welcomeurl;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        return !subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (SecurityUtils.getSubject().hasRole("Administrator")) {
            welcomeurl="/faces/homeA.xhtml";
        } else if(SecurityUtils.getSubject().hasRole("Usuario")){
            welcomeurl="/faces/homeA.xhtml";
        }

        WebUtils.issueRedirect(servletRequest, servletResponse, welcomeurl);
        return false;
    }

    public String getWelcomeurl() {
        return welcomeurl;
    }

    public void setWelcomeurl(String welcomeurl) {
        this.welcomeurl = welcomeurl;
    }
}
