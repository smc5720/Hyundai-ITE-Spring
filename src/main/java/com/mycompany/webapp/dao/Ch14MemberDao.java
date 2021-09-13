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

	public void insert(Ch14Member member) {
		logger.info("실행");
		sqlSessionTemplate.insert("member.insert", member);
	}

	public Ch14Member selectByMid(String mid) {
		return sqlSessionTemplate.selectOne("member.selectByMid", mid);
	}
}
