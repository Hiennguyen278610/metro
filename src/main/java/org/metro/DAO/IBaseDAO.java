package org.metro.DAO;

import java.util.List;

/**
 * Interface DAO dùng chung cho mọi đối tượng
 */
public interface IBaseDAO<T> {
    int insert(T t);
    int update(T t);
    int delete(int id);
    List<T> selectAll();
    T selectById(int id);
}