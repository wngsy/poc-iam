package wn.demo.pociam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wn.demo.pociam.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

    public Optional<Note> findNoteByUseridAndId(String userid, Long id);
    public List<Note> findByUseridOrderByAuditUpdatedOn(String userid);
}
