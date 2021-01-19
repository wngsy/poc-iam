package wn.demo.pociam.dto;

import java.util.Date;

public class NoteDto {
        private Long id;
        private String title;
        private Date createdDate;
        private Date updatedDate;
        public NoteDto(Long id, String title, Date createdDate, Date updatedDate) {
            super();
            this.id = id;
            this.title = title;
            this.createdDate = createdDate;
            this.updatedDate = updatedDate;
        }
        public NoteDto() {}
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public Date getCreatedDate() {
            return createdDate;
        }
        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }
        public Date getUpdatedDate() {
            return updatedDate;
        }
        public void setUpdatedDate(Date updatedDate) {
            this.updatedDate = updatedDate;
        }
        
        
        
}
