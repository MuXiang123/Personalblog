package com.pb.personalblog.service;

import com.pb.personalblog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TypeService {
    //    保存分类
    Type saveType(Type type);

    //    通过id查询分类
    Type getType(Long id);

    //    通过name查询分类
    Type getTypeByName(String name);

    //    分页查询
    Page<Type> listType(Pageable pageable);

    //返回全部
    List<Type> listType();

    //    修改分类
    Type updateType(Long id, Type type);

    //    删除分类
    void deleteType(Long id);
}
