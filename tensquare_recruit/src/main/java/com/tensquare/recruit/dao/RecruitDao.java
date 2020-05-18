package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    /**
     * State不能等于0，根据时间字段的排序，倒叙查询，并且只展示4条记录
     * @param state
     * @return
     */
    List<Recruit> findTop4ByStateNotOrderByCreatetimeDesc(String state);

    /**
     * 查询状态为2，并且以创建时间到倒叙，并且要求展示前4条的记录
     * @param state
     * @return
     */
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);
}
