package Dao.IDao;

import Dao.ValueObject.Book_class;

import java.util.List;

/**
 * Created by dela on 7/22/17.
 */
public interface Book_classDao {
    //取得所有的父标签, 即按parent_id查询的所有数据
    public List<Book_class> getBookClasses(int parent_id);

    //插入新的标签
    public void insertClass(String labelName, int parentId);

    //通过标签名找到标签ID
    public int queryIdByName(String labelName);

}
