package test;

import com.opensymphony.xwork2.ActionSupport;

public class Base extends ActionSupport {


    @Override
    public String execute(){
        return "success";
    }
}
