package com.MiniContentGenerator.ContentGenerator.service;

import com.MiniContentGenerator.ContentGenerator.dto.ProductRequest;
import com.MiniContentGenerator.ContentGenerator.model.ContentEntity;
import com.MiniContentGenerator.ContentGenerator.repository.ContentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        /*
         Instead of this dummy string, we now want to simulate what an AI-generated response would look like
         (like how OpenAI gives JSON or text back). We'll:
         Simulate an AI response as a structured JSON/text string. Parse it.
         Store relevant fields (like description/content) into ContentEntity.
          String content = "This is the content for this entity";
         */
        // here jsonResponse is being hard-coded to mimic the response that we wil be getting from GPT
        // TODO: Replace jsonResponse with actual OpenAI integration later.
        // NOTE: We’re simulating this until GPT is integrated

        String jsonResponse = "{ \"content\": \"This is the generated content\", \"summary\": \"Quick summary\", \"keywords\": [\"fashion\", \"style\"] }";
        // now with the help of ObjectMapper, we will parse this jsonResponse
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // to extract each and every content, just like a hashmap,
            // we are first, creating a pointer to access, nodes and then their values
            // now get(node) is like asking give the value of that node (key)
            // note, now the "parsed" values will be in the format, of our convenience
            // so we are storing them as string
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            String content = rootNode.get("content").asText();
            String summary = rootNode.get("summary").asText();
            // however there's something more in "keywords" section
            JsonNode keywordsNode = rootNode.get("keywords");
            /*
            ✅ keywordsNode now holds a JSON array node, like:
            ["fashion", "style"]
            It's not a String, it’s still a Jackson structure (ArrayNode) of 2 string elements.
            Now our, Goal is to convert this JSON array:
            "fashion", "style"]
            Into a simple comma-separated string:
            "fashion, style"
            This string will go into your DB field keywords (which is a simple String column).
             */
            List<String> keywordsList = new ArrayList<>();
            for(JsonNode keywordNode: keywordsNode){
                // this keywordList will be like ["fashion", "style"]
                keywordsList.add(keywordNode.asText());
            }
            // this keywordsStr will look like  "fashion, style"
            String keywordsStr = String.join(", ", keywordsList);

            // now finally storing jsonResponse extracted in the format as we need in the backend and for the db
            entity.setContent(content);
            entity.setSummary(summary);
            entity.setKeywords(keywordsStr);
            entity.setGeneratedContent(jsonResponse);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

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

