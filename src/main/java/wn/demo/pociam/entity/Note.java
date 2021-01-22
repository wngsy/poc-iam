package wn.demo.pociam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(indexes = @Index(name = "userid_index" , columnList = "userid"))
public class Note extends BaseEntity{

    @Column(nullable = false)
    private String userid;
    @Column(nullable = false)
    private String title;


    
    public Note() {
        super();
    }
    
    public Note(String userid, String title) {
        this.userid = userid;
        this.title = title;
    }
    


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        super.getAudit().update();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Note [userid=" + userid + ", title=" + title + ", toString()=" + super.toString() + "]";
    }

    
    



    
    
    
    
    
}   
