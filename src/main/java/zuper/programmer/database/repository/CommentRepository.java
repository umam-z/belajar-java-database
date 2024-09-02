package zuper.programmer.database.repository;

import zuper.programmer.database.entity.Comment;

import java.util.List;

public interface CommentRepository {
    void insert(Comment comment);

    Comment findById(Integer id);

    List<Comment> findAll();

    List<Comment> findAllByEmail(String email);

}
