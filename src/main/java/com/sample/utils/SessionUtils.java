package  com.sample.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.security.Key;

public class SessionUtils {

    public void setSessionValue(HttpServletRequest request, String key, Object value) {
        HttpSession session = (HttpSession) request.getSession(true);
        session.setAttribute(key, value);
    }

    public Object getSessionValue(HttpServletRequest request, String key) {
        HttpSession session = (HttpSession) request.getSession(true);
        return session.getAttribute(key);
    }

    public String getSessionStringValue(HttpServletRequest request, String key) {
        HttpSession session = (HttpSession) request.getSession(true);
        return session.getAttribute(key) == null ? "" : session.getAttribute(key).toString();
    }

    // public  Authobjects getAuthAccount(HttpServletRequest
    // request) {
    // HttpSession session = (HttpSession) request.getSession(true);
    // return (Authobjects) session.getAttribute("authaccount");
    // }
    public void removeSessionValue(HttpServletRequest request, String key) {
        HttpSession session = (HttpSession) (HttpSession) request.getSession(true);
        session.removeAttribute(key);
    }

    public String getSessionId(HttpServletRequest request) {
        HttpSession session = (HttpSession) (HttpSession) request.getSession(false);
        return session.getId();
    }

    public void invalidate(HttpServletRequest request) {
        HttpSession session = (HttpSession) (HttpSession) request.getSession(false);
        if (session != null) {
            session.removeAttribute("authaccount");
            session.invalidate();
        }
    }

    public HttpSession getSession(HttpServletRequest request, boolean flag) {
        return request.getSession(flag);
    }

    public String getRequestIPAddress(HttpServletRequest request) {
        String ipaddress = null;
        ipaddress = request.getHeader("X-FORWARDED-FOR");
        if (ipaddress == null) {
            ipaddress = request.getRemoteAddr();
        }
        return ipaddress;
    }

    public String hostName(HttpServletRequest request) {
        String hostname = null;
        hostname = request.getRemoteHost();
        return hostname;
    }

    public Key getKey(HttpServletRequest request, String key) {
        HttpSession session = getSession(request, false);
        if (session != null) {
            return (Key) session.getAttribute(key);
        }
        return null;
    }

    public void setSessionExpirdTime(HttpServletRequest request, int min) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(min * 60);
    }

    public int getSessionExpirdTime(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getMaxInactiveInterval();
    }

}
