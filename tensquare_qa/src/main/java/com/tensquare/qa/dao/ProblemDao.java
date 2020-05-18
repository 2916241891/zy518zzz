package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 最新问答列表
     * 根据最新回答时间进行倒序查询
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in (select problemid from Pl where labelid=?1) order by replytime desc")
    public Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);

    /**
     * 热门问答列表
     * 根据回复数进行倒序查询
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select p.* from tb_problem p where id in(select problemid from tb_pl where labelid=?1 ) order by p.reply desc",nativeQuery = true)
    Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);

    /**
     * 等待问答列表
     * 获取回复数为0的数据
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in (select problemid from Pl where labelid=?1) and reply=0  order by createtime desc")
    Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);
}
