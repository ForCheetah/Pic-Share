package whs.intercepter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import whs.user.domain.User;

public class LoginIntercepter extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if(existUser!=null){
            return actionInvocation.invoke();
        }
        else{
            ActionSupport action= (ActionSupport) actionInvocation.getAction();
            action.addActionMessage("请先登录");
            return "loginFiled";
        }
    }
}
