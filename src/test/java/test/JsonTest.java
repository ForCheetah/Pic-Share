package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import whs.user.dao.UserDao;
import whs.user.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonTest {
    public static void main(String[] args){
        User user=new User();
        user.setUid("156165465165315sfasdf");
        user.setUimg("1565451sahfhdaihf");
        user.setUlogintime(new Date(111151111));
        user.setUphoto("这是一张图片的地址");
        user.setMotto("i am a good man");
        user.setUexpress("may be");
        user.setUage(50);

        User user1=new User();
        user1.setUage(15);
        user1.setUexpress("wozyevabdf");
        user1.setMotto("shenfgaoigdhfa");
        user1.setUphoto("sheaiohfuaihbci");
        user1.setUlogintime(new Date(157165454));




        JSONObject json=JSONObject.fromObject(user);
        JSONArray jsonArray=JSONArray.fromObject(user);
        String jA=json.toString();
        String jB=jsonArray.toString();
        System.out.println(jA+"............"+jB);
        System.out.println("接下来要将其转换会对象，我觉得有点悬，毕竟该对象中有一个比较复杂的变量，也就是datel类型");

        JSONObject js=JSONObject.fromObject(jA);
        User newUSer= (User) JSONObject.toBean(js,User.class);
        System.out.println(newUSer.toString());

        JSONArray jsa=JSONArray.fromObject(jB);
        Object object=jsa.get(0);
        JSONObject jjss=JSONObject.fromObject(object);
        User uu = (User) JSONObject.toBean(jjss,User.class);
        System.out.println(uu.toString());

        System.out.println("这里将进行集合到字符串的转换");
        List<User> list=new ArrayList<User>();
        list.add(user);
        list.add(user1);
        JSONArray listArray=JSONArray.fromObject(list);
        String strOFArray=listArray.toString();
        System.out.println(listArray.toString());

        System.out.println("这里将进行字符串到集合的转换");


        List<User> zhuanhuanList=JSONArray.toList(JSONArray.fromObject(strOFArray),User.class);
        for(User user2:zhuanhuanList){
            System.out.println(user2.toString());
        }


//        创建list和json的相互反转



    }
}
