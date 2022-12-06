package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Optional;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getPostComments(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable long postId, @PathVariable long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(path = "/{postId}/comments")
    public Comment createComment(@RequestBody Comment comment, @PathVariable long postId) {
        Optional<Post> post = postRepository.findById(postId);
        Post p = post.orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        comment.setPost(p);
        return commentRepository.save(comment);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable long postId, @RequestBody Comment comment, @PathVariable long commentId) {

        if (!isCommentExist(commentId, postId)) {
            throw new ResourceNotFoundException("Comment not found");
        }

        Comment c = commentRepository.findByIdAndPostId(commentId, postId).get();
        c.setId(commentId);
        c.setContent(comment.getContent());
        return commentRepository.save(c);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {

        if (!isCommentExist(commentId, postId)) {
            throw new ResourceNotFoundException("Comment not found");
        }

        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
    }

    private boolean isCommentExist(long commentId, long postId) {
        return commentRepository.findByIdAndPostId(commentId, postId).isPresent();
    }
    // END
}
