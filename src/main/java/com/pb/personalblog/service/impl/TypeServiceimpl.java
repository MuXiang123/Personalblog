package com.pb.personalblog.service.impl;


import com.pb.personalblog.exception.NotFoundException;
import com.pb.personalblog.pojo.Type;
import com.pb.personalblog.repository.TypeRepository;
import com.pb.personalblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分类方法实现
 */
@Service
public class TypeServiceimpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Transactional//增删改查全都放在事务内
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).get();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }


    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blog.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findById(id).get();//先根据id查询到对象
        if (t == null) {
            throw new NotFoundException("不存在该类型");//如果为空抛出异常
        }
        BeanUtils.copyProperties(type, t);//将输入的type内容复制到查询到的t中
        return typeRepository.save(t);//更新修改
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
