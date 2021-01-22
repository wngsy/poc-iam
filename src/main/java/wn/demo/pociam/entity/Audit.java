package wn.demo.pociam.entity;


import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Embeddable
//@EntityListeners(AuditingEntityListener.class)
public class Audit {

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    
    public Audit() {
        this.createdOn = new Date();
        this.updatedOn = new Date();        
    }
    
    
    public void update() {
        this.updatedOn = new Date();
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Audit [createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
    }
    
    
    
    
}
