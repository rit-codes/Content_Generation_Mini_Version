package com.MiniContentGenerator.ContentGenerator.service;

import com.MiniContentGenerator.ContentGenerator.dto.TaxonomyResponse;
import org.springframework.stereotype.Service;

@Service
public class TaxonomyService {

    public TaxonomyResponse getTaxonomyResponse(String templateName){
        TaxonomyResponse taxonomyResponse = new TaxonomyResponse();

        switch (templateName.toLowerCase()){
            case "fashion"->{
                taxonomyResponse.setTone("informative");
                taxonomyResponse.setLength("250 words");
                taxonomyResponse.setAuthor("AI");
            }

            case "electronics"->{
                taxonomyResponse.setTone("technology");
                taxonomyResponse.setLength("300 words");
                taxonomyResponse.setAuthor("TechBot");
            }

            default->{
                taxonomyResponse.setTone("generic");
                taxonomyResponse.setLength("200 words");
                taxonomyResponse.setAuthor("AutoGen");
            }
        }
        return taxonomyResponse;
    }
}
