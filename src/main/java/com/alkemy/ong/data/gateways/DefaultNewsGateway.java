package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.NewsEntity;
import com.alkemy.ong.data.repositories.NewsRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.news.News;
import com.alkemy.ong.domain.news.NewsGateway;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class DefaultNewsGateway implements NewsGateway {

    private final NewsRepository newsRepository;
    public DefaultNewsGateway(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    public News findById(Long id) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        optional.orElseThrow(() -> new ResourceNotFoundException("ID"));
        News returnModel = this.toModel(optional.get());
        return returnModel;
    }

    @Override
    public News save(News news) {
        NewsEntity newsEntity = this.toEntity(news);
        News returnModel = this.toModel(newsRepository.save(newsEntity));
        return returnModel;
    }

    @Override
    public News getDetails(Long id) {
        return this.findById(id);
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> findByPage(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return newsRepository.findAll(pageable)
                .stream()
                .map(p -> toModel(p))
                .collect(toList());
    }

    private NewsEntity toEntity(News news){
        NewsEntity returnEntity = NewsEntity.builder()
                .id(news.getId())
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .categoryId(news.getCategoryId())
                .type(news.getType())
                .build();
        return returnEntity;
    }

    private News toModel(NewsEntity newsEntity){
        News returnModel = News.builder()
                .id(newsEntity.getId())
                .name(newsEntity.getName())
                .content(newsEntity.getContent())
                .image(newsEntity.getImage())
                .categoryId(newsEntity.getCategoryId())
                .type(newsEntity.getType())
                .build();
        return returnModel;
    }
}
