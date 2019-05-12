现在还有几个小问题还没有解决
1.UserAction 中的uid获取不到，通过get方法，
   前面明明在picture中的PAge可以获取得到？？？？？
  最后不得不通过request得到的？？？？？为什么呢？？？？？
  可能是idea临时出了bug

2.也是问题多多啊
    首先图片上传时候，PersionPage打开的图片上传窗口的选择按钮：火狐只能按左上角，比较不灵敏，其他浏览器干脆只能在放大缩小窗口之后，才能按
    然后Folw窗口开始有点乱（好像是最后一张图片老是漂在左边） 之后恢复正常，然后就是 只有火狐浏览器可以用鼠标滚动，其余浏览器都不行

3.在浏览器上雪花动画有问题
显示不出来


4.在pool页面上的两个userId都是写死的，记得要改。

5.真是给跪了，TM 在POOL页面中和PersionalPool页面中，给图片添加链接根本不管用，tMd如何是好？？？？？？



将hibernate，Spring升级到5.0和4.0，真是要了我的狗命了，fuck，真是的，在坑里一坐不起，差点死在里面。
注意：
    1.现在打算将redis数据库直接使用远端服务器的，到时候要改过来：部署到服务器上面的时候就要使用本地服务器了
    2.现在用的servlet-api是仅在测试的环境下使用，没有参与到部署，有可能在部署的时候失败。
    3.import org.springframework.orm.hibernate5.support.HibernateDaoSupport;，记着，改了5.0之后，所有的都要是5.0的内容，包括Spring配置文件和DAo层的Java代码
    4.struts2.5之后，默认不再支持Action的通配符，所以要在package中添加strict-method-invocation="false"
    5.maven项目中需要自定义添加jstl包，用于jsp文件的标签使用。
    6.hql在5.0中可以使用，但是有些this.getHibernateTemplate已经不太好使了。
    7.maven中也没有添加用于日志的jar包 ，需要自己添加log4j2
    8.log4j2的配置文件改成xml,了，可能其余的也能用，但是xml可用。
    9.在将要部署到实际服务器上面是，记得更改jedis连接池的参数
    10.在服务器上面部署不起来，是因为数据库连接的数据库名字填错了，应该是whsnet,而不是whsNet我的本地和服务器上面的数据库名除了一点差错，草！

    11.该版本，打成war包之后，还要修改密码为%wewillwin,以及数据库为 whsnetnew