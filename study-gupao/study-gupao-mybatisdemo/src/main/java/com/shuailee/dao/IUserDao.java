package com.shuailee.dao;


import com.shuailee.model.User;
import com.shuailee.model.UserModel;
import org.apache.ibatis.annotations.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface IUserDao {

     /*********************使用跟数据库字段相同的dao对象映射，MyBatis 可以通过类型处理器推断出具体传入语句的参数**************************/
     /**
      * 查询所有数据
      * */
/*     @ResultType(User.class)
     @Select("select * from tb_user")*/
     List<User> queryUser();
     /**
      * 根据id查询用户
      * */
     HashMap<String,Object> queryUserByID(Integer id);

     /**
      * 插入一条记录
      * */
    /* @Options(useGeneratedKeys = true, keyProperty = "user_id")
     @Insert("insert into tb_user (user_name,user_sex,user_birthday,user_address,user_email)\n" +
             "         values (#{user_name},#{user_sex},#{user_birthday},#{user_address},#{user_email})")*/
     int insertUser (User user);
     /**
      * 插入一个列表
      * */
     void batchInsertUser(List<User> users);
     /**
      * 更改一条数据
      * */
     int updateUser (User user);

     /**
      * 删除一条数据
      * */
     int deleteUserByID (Integer id);


     /*********************使用不同的dao对象映射**************************/

     /**
      * 多条件查询，使用不同的dao对象映射
      * */
     List<UserModel> queryUserinfo(Map<String,Object> parameter);

     /**
      * 多条件查询,使用不同的dao对象映射
      * */
     List<UserModel> queryUserinfo2(Map<String,Object> parameter);

     /**
      * 更新
      * */
     int updateUserById(UserModel userModel);

     int updateUserById3 (UserModel userModel);


     List<User> queryUserbyids(List<String> ids);

}
