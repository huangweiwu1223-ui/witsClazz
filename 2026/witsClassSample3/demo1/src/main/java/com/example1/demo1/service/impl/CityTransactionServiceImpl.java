package com.example1.demo1.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example1.demo1.entity.City;
import com.example1.demo1.mapper.CityMapper;
import com.example1.demo1.service.CityTransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link CityTransactionService} 的實作。
 * <p>
 * 透過 MyBatis-Plus 的 {@link CityMapper} 完成資料庫存取。
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CityTransactionServiceImpl implements CityTransactionService {

    private final CityMapper cityMapper;
    
    /**
     * 正常交易控制
     */
    @Transactional
    public void doTransactionTest1(City city1, City city2){

        int updRst = cityMapper.updateById(city1);

        log.error("doTransactionTest1 update1, upd cnt:{}", updRst);

        updRst = cityMapper.updateById(city2);

        log.error("doTransactionTest1 update2, upd cnt:{}", updRst);

    }

    /**
     * 加入第三個參數, 故意中斷交易
     * @param city1
     * @param city2
     * @param isThrowsException
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void doTransactionTest2(City city1, City city2, boolean isThrowsException){

        int updRst = cityMapper.updateById(city1);

        log.error("doTransactionTest1 update1, upd cnt:{}", updRst);

        updRst = cityMapper.updateById(city2);

        // 遇到不順的事，中斷交易。
        if (isThrowsException){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            
        }

        log.error("doTransactionTest1 update2, upd cnt:{}", updRst);

    }

    /**
     * 加入 try-catch，使用 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 中斷交易
     * @param city1
     * @param city2
     */
    @Transactional(rollbackFor = Exception.class)
    public void doTransactionTest3(City city1, City city2) throws Exception{

        try {
            int updRst = cityMapper.updateById(city1);
    
            log.error("doTransactionTest1 update1, upd cnt:{}", updRst);
    
            updRst = cityMapper.updateById(city2);

            throw new Exception("故意拋錯，中止交易");
            
        } catch (Exception e) {
            log.error("使用【TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();】終止交易, e:{}", e.getMessage());
            throw new Exception("故意拋出 Exception, 中斷交易");
        }

    }

}
