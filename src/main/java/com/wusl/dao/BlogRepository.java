package com.wusl.dao;

import com.wusl.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {


    @Query("select b from t_blog b where b.recommend=true")
    List<Blog> findTop(Pageable page);

    @Query("select b from  t_blog b where b.title like ?1 or b.jianje like ?1")
    Page<Blog> findByQuery(String query, Pageable page);

    @Transactional
    @Modifying
    @Query("update t_blog b set b.views=b.views+1 where b.id=?1")
    int updateViews(Long id);

    @Query(value = "select DATE_FORMAT(b.update_time,'%Y') as year  from t_blog b GROUP BY year ORDER BY year desc ",nativeQuery = true)
    List<String> findGroupYear();

    @Query(value = "select *from t_blog b where DATE_FORMAT(b.update_time,'%Y') = ?1 ",nativeQuery = true)
    List<Blog> findByYear(String year);


}
