package com.wusl.service;

import com.wusl.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    /*新增*/
    Type saveType(Type type);

    /*获取*/
    Type getType(Long id);

    /*分页*/
    Page<Type> listType(Pageable pageable);

    /*修改*/
    Type updateType(Long id,Type type);

    /*删除*/
    void deleteType(Long id);

    /*查重*/
    Type getTypeByName(String name);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

}
