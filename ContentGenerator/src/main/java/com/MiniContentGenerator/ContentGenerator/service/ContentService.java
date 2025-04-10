package com.MiniContentGenerator.ContentGenerator.service;

import com.MiniContentGenerator.ContentGenerator.dto.ProductRequest;
import com.MiniContentGenerator.ContentGenerator.model.ContentEntity;
import com.MiniContentGenerator.ContentGenerator.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    // Note: Highlight here I need to save the entity which is created and filled into DB
    // so we need instance of ContentRepository, and hence we need Autowire
    @Autowired
    private ContentRepository contentRepository;

    public String generateContent(ProductRequest request) {

        // feeding the request received from api (ProductRequest request and saving it into entity,
        // now this entity will be saved into db
        ContentEntity entity = new ContentEntity();
        entity.setEntityID(request.entityId());
        entity.setContent(request.concept());
        entity.setTemplateName(request.templateName());
        // for now, we are taking content in here, later will take content from response
        // dummy
        String content = "This is the content for this entity";
        entity.setContent(content);
        ContentEntity savedEntity = contentRepository.save(entity);
        return "Saved content with DB ID: " + savedEntity.getId();
    }

    public String getContentById(Long id){
        Optional<ContentEntity> optionalContentEntity = contentRepository.findById(id);
        if (optionalContentEntity.isPresent()) {
            ContentEntity entity = optionalContentEntity.get();
            return "Fetched content for entity: " + entity.getEntityID();
        }
        else{
            return "Entity not found for ID: " + id;
        }
    }

    public List<ContentEntity> getAllContent(){
        return contentRepository.findAll();
    }


}






    // Note: Here I did not initialise ContentEntity using @Autowired, since its POJO (Plain Old Java Object)
        /*
        💡 Rule of Thumb:
           Component Type	Managed by Spring?	Use @Autowired?	Use new?
           Spring-managed component,
           Spring creates a bean (an object) of it at runtime automatically,
           using a proxy class under the hood, examples -
           @Service,        ✅ Yes	                  ✅ Yes	❌ No
           @Repository,     ✅ Yes	                  ✅ Yes	❌ No
           @Component,      ✅ Yes	                  ✅ Yes	❌ No
           @Controller	    ✅ Yes	                  ✅ Yes	❌ No
           ---------------------------------------------------------------
           POJO (Plain Old Java Object).
           It's just a simple class that holds data,
           not a Spring-managed bean.
           Must not be initialed by Spring annotations, as, we may get
           weird bugs or errors later
           (because Spring doesn’t manage it — it doesn’t know how to inject this) examples.
           ContentEntity,
           ProductRequest,  ❌ No	                  ❌ No	   ✅ Yes
           DTOs	            ❌ No	                  ❌ No	   ✅ Yes
         */

          /*
            🧠 A Clearer Definition:
            Optional<T> is a container object which may or may not contain a non-null value of type T.
            If the value is present, .get() gives the value.
            If the value is not present, it prevents NullPointerExceptions by letting you handle the absence safely.
           */

