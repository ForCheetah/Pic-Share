package utils;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
    @Override
    public String execute(){
        return "test";
    }
}
