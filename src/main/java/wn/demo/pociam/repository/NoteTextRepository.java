package wn.demo.pociam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wn.demo.pociam.entity.NoteText;

@Repository
public interface NoteTextRepository extends JpaRepository<NoteText, Long>{

    Optional<NoteText> findNoteTextByIdAndNoteUserid(Long id, String userid);
}
