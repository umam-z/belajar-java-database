package zuper.programmer.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zuper.programmer.database.entity.Comment;
import zuper.programmer.database.repository.CommentRepository;
import zuper.programmer.database.repository.CommentRepositoryImpl;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("fulan@tast","hi");
        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById(2303);
        Assertions.assertNotNull(comment);
        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comment comment1 = commentRepository.findById(12);
        Assertions.assertNull(comment1);
    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();
        System.out.println(comments.size());
    }

    @Test
    void testFindByEmail() {
        List<Comment> comments = commentRepository.findAllByEmail("fulan@tast");
        System.out.println(comments.size());
    }
}
