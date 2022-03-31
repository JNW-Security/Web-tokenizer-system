package edu.eci.cvds;


import static org.junit.Assert.assertTrue;


import org.apache.ibatis.session.SqlSession;

import javax.inject.Inject;

public class AppTest 
{

    @Inject
    private SqlSession sqlSession;

}