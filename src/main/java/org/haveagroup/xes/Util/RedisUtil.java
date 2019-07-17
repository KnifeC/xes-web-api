package org.haveagroup.xes.Util;


import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.QuestionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Object getObject(String key) {
        return (Object)redisTemplate.opsForValue().get(key);
    }

    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

//    public List<Question> getQuestionList(String key){
//        List<Question> questionList = (List<Question>)redisTemplate.opsForValue().get(key);
//        return questionList;
//    }
//
//    public List<Object> getObjectList(String key){
//        return (List<Object>)redisTemplate.opsForList();
//    }
//
//    public void setObjectList(String key,List<Object> value){
//        redisTemplate.opsForValue().set(key,value);
//    }
}
