package com.phuongtd.blog.services;

import com.phuongtd.blog.entities.Post;
import com.phuongtd.blog.entities.Tag;
import com.phuongtd.blog.repositories.CategoryRepository;
import com.phuongtd.blog.repositories.PostRepository;
import com.phuongtd.blog.repositories.TagRepository;
import com.phuongtd.blog.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    public Date getCurrentTime() throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = LocalDateTime.now().toString();
        String date = datetime.substring(0, 10);
        String time = datetime.substring(11, 19);
        System.out.println(date + " " + time);
        return formatter1.parse(date + " " + time);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findByCategory(int categoryId) {
        List<Post> allPost = postRepository.findByCategory(categoryRepository.findById(categoryId));
        List<Post> activePost = new ArrayList<>();
        for (Post post : allPost){
            if (post.is_active()) activePost.add(post);
        }
        return activePost;
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public Post save(Post post) throws ParseException {
        post.setCreatedAt(getCurrentTime());
        post.set_active(false);
        List<Tag> tagsRequest = post.getTagList();
        List<Tag> tagsExist = new ArrayList<>();

        for (Tag tag : tagsRequest) {
            Tag tagTmp = tagRepository.findByName(tag.getName());
            if (tagTmp != null) {
                tagsExist.add(tagTmp);
            } else {
                tagsExist.add(tagRepository.save(tag));
            }
        }
        post.setTagList(tagsExist);
        return postRepository.save(post);
    }

    public List<Post> findByTag(String tagName) {
        return postRepository.findByTagList_Name(tagName);
    }

    public Post deletePost(int id) throws NotFoundException {
        try {
            Post post = postRepository.findById(id).get();
            postRepository.deleteById(id);
            return post;
        } catch (Exception e) {
            throw new NotFoundException("Not found exception");
        }
    }

    public Post update(int id, Post newPost) throws ParseException, NotFoundException {
        Optional<Post> oldPost = postRepository.findById(id);
        if (oldPost.isPresent()) {
            oldPost.get().setTitle(newPost.getTitle());
            oldPost.get().setContent(newPost.getContent());
            oldPost.get().setCategory(newPost.getCategory());
            oldPost.get().setUpdatedAt(getCurrentTime());
            oldPost.get().setImg_thump_url(newPost.getImg_thump_url());
            List<Tag> tagsRequest = newPost.getTagList();
            List<Tag> tagsExist = new ArrayList<>();

            for (Tag tag : tagsRequest) {
                Tag tagTmp = tagRepository.findByName(tag.getName());
                if (tagTmp != null) {
                    tagsExist.add(tagTmp);
                } else {
                    tagsExist.add(tagRepository.save(tag));
                }
            }
            oldPost.get().setTagList(tagsExist);
            return postRepository.save(oldPost.get());
        }
        throw new NotFoundException("Not found exception");
    }

    public List<Post> findByUser(int id) {
        return postRepository.findByUser(userRepository.findById(id));
    }
}