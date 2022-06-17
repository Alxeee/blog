package com.wusl.service;

import com.wusl.pojo.Tag;
import com.wusl.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    /*新增*/
    Tag saveTag(Tag tag);

    /*获取*/
    Tag getTag(Long id);

    /*分页*/
    Page<Tag> listTag(Pageable pageable);

    /*修改*/
    Tag updateTag(Long id, Tag tag);

    /*删除*/
    void deleteTag(Long id);

    /*查重*/
    Tag getTagByName(String name);


    List<Tag> listTag();

    /*查询多条*/
    List<Tag> listTag(String ids);


    List<Tag> lisTagTop(Integer size);

}
