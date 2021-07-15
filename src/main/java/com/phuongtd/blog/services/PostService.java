package com.phuongtd.blog.services;

import com.phuongtd.blog.entities.Post;
import com.phuongtd.blog.repositories.CategoryRepository;
import com.phuongtd.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;
    public Date getCurrentTime() throws ParseException {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = LocalDateTime.now().toString();
        String date = datetime.substring(0,10);
        String time = datetime.substring(12,19);
        return formatter1.parse(date+ " "+time);
    }
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public List<Post> findByCategory(int categoryId){
        return postRepository.findByCategory(categoryRepository.findById(categoryId));
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public Post save(Post post) throws ParseException {
        post.setCreatedAt(getCurrentTime());
        post.set_active(false);
        return postRepository.save(post);
    }

    public List<Post> findByTag(String tagName){
        return postRepository.findByTagList_Name(tagName);
    }

    public Post deletePost(int id) {
        try {
            Post post = postRepository.findById(id).get();
            postRepository.deleteById(id);
            return post;
        } catch (Exception e) {
            return null;
        }
    }
    public Post update(int id, Post newPost) throws ParseException {
        Optional<Post> oldPost = postRepository.findById(id);
        if (oldPost.isPresent()){
            oldPost.get().setTitle(newPost.getTitle());
            oldPost.get().setContent(newPost.getContent());
            oldPost.get().setCategory(newPost.getCategory());
            oldPost.get().setUpdatedAt(getCurrentTime());
            oldPost.get().setTagList(newPost.getTagList());
            oldPost.get().setImg_thump_url(newPost.getImg_thump_url());
            return oldPost.get();
        }
        return null;
    }
}
