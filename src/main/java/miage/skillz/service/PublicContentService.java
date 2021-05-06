package miage.skillz.service;

import miage.skillz.entity.PublicContent;
import miage.skillz.repository.BadgeRepository;
import miage.skillz.repository.PublicContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicContentService {
    @Autowired
    private PublicContentRepository repository;

    public void savePublicContent(PublicContent content) {
        repository.save(content);
    }

    public List<PublicContent> getPublicContentList() {
        return  repository.findAll();
    }
}
