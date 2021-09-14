package com.mycompany.webapp.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14Member;

@Repository
public class Ch14MemberDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch14MemberDao.class);

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public int insert(Ch14Member member) {
		logger.info("실행");
		// 몇 개의 행이 insert 되었는가를 return 한다.
		int rows = sqlSessionTemplate.insert("mybatis.mapper.member.insert", member);
		return rows;
	}

	public Ch14Member selectByMid(String mid) {
		return sqlSessionTemplate.selectOne("mybatis.mapper.member.selectByMid", mid);
	}
}
