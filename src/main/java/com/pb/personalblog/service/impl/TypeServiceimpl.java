package com.pb.personalblog.service.impl;

import com.pb.personalblog.exception.NotFoundException;
import com.pb.personalblog.pojo.Type;
import com.pb.personalblog.repository.TypeRepository;
import com.pb.personalblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

//分类方法实现
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

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        //先根据id查询到对象
        Type t = typeRepository.findById(id).get();
        if (t == null) {
            //如果为空抛出异常
            throw new NotFoundException("不存在该类型");
        }
        //将输入的type内容复制到查询到的t中
        BeanUtils.copyProperties(type, t);
        //更新修改
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
