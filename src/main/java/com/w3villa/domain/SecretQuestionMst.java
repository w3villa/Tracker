package com.w3villa.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SecretQuestionMst generated by hbm2java
 */
@Entity
@Table(name="secret_question_mst"
    )
public class SecretQuestionMst  implements java.io.Serializable {


     private Integer id;
     private String questionDescription;
     private String createdBy;
     private Date createdDate;
     private String updatedBy;
     private Date updatedDate;

    public SecretQuestionMst() {
    }

	
    public SecretQuestionMst(String questionDescription) {
        this.questionDescription = questionDescription;
    }
    public SecretQuestionMst(String questionDescription, String createdBy, Date createdDate, String updatedBy, Date updatedDate) {
       this.questionDescription = questionDescription;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.updatedBy = updatedBy;
       this.updatedDate = updatedDate;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="question_description", nullable=false, length=200)
    public String getQuestionDescription() {
        return this.questionDescription;
    }
    
    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", length=19)
    public Date getcreatedDate() {
        return this.createdDate;
    }
    
    public void setcreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_date", length=19)
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }




}

