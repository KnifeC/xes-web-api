package org.haveagroup.xes.Dal.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "paper_question_cache")
public class Paper_Question_Cache implements Serializable {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String pqcId;

    private String paperCacheId;
    private String questionCacheId;

    public String getPqcId() {
        return pqcId;
    }

    public void setPqcId(String pqcId) {
        this.pqcId = pqcId;
    }

    public String getPaperCacheId() {
        return paperCacheId;
    }

    public void setPaperCacheId(String paperCacheId) {
        this.paperCacheId = paperCacheId;
    }

    public String getQuestionCacheId() {
        return questionCacheId;
    }

    public void setQuestionCacheId(String questionCacheId) {
        this.questionCacheId = questionCacheId;
    }
}
