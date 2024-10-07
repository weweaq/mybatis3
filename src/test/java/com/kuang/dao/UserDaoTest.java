package com.kuang.dao;

import com.kuang.pojo.User;
import com.kuang.utils.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDaoTest {

    /**
     * 参考
     https://wch853.github.io/posts/

     https://zhuanlan.zhihu.com/p/97879019

     机场
     https://jinkela.dad/user/shop

     https://limbopro.com/archives/v2ray-jinkela.html#gsc.tab=0
     * @throws Exception
     */
    @Test
    public void testZhiHu() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            /*UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUserById(1);
            System.out.println(user);*/
            Object o = session.selectOne("com.kuang.dao.UserMapper.getUserById", 1);
            System.out.println("我是第一次查询的"+o);
            System.out.println("-------------------------------我是分割线---------------------");
            Object z = session.selectOne("com.kuang.dao.UserMapper.getUserById", 1);
            System.out.println("我是第二次查询的"+z);
           /*User user = new User();
           user.setAge(15);
           user.setName("achuan");
           int insert = session.insert("com.wsdsg.spring.boot.analyze.mapper.UserMapper.addOneUser", user);
           session.commit();
           System.out.println(insert);*/
        } finally {
            session.close();
        }
    }

    @Test
    public void test(){
        //第一步:获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一:getMapper
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);

        List<User> userList = userDao.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }

        //关闭SqlSession
        sqlSession.close();

    }



    @Test
    public  void getUserById (){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);

        sqlSession.close();

    }
    @Test
    public  void getUserById2 (){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("id" ,"1");
        mapper.getUserById2(map);

        sqlSession.close();

    }



         @Test
        public void addUser(){
             SqlSession sqlSession = MybatisUtils.getSqlSession();
             UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           mapper.addUser(new User(4,"哈哈","163554"));


             sqlSession.commit();
             sqlSession.close();


         }

    @Test
    public void addUser2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String,Object> map= new HashMap<String,Object>();
        map.put("userid" ,5);
        map.put("username" ,"小熊");
        map.put("password" ,"465415");



        mapper.addUser2(map);


        sqlSession.commit();
        sqlSession.close();


    }




    @Test
    public  void deleteUser(){
             SqlSession sqlSession = MybatisUtils.getSqlSession();
             UserMapper mapper = sqlSession.getMapper(UserMapper.class);
             mapper.deleteUser(4);
             sqlSession.commit();
             sqlSession.close();
         }

         @Test
    public void updateUser(){
             SqlSession sqlSession = MybatisUtils.getSqlSession();
             UserMapper mapper = sqlSession.getMapper(UserMapper.class);
             mapper.updateUser(new User(4,"呵呵","456655"));
             sqlSession.commit();
             sqlSession.close();
         }




}
