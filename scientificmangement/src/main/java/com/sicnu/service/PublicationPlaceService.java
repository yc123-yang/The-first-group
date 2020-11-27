package com.sicnu.service;

import com.sicnu.pojo.PublicationPlace;
import com.sicnu.util.Result;

public interface PublicationPlaceService {
    Result addPublicationPlace(String pp_name);
    Result delPublicationPlace(Integer pp_id);
    Result findAllPublicationPlace();
    Result updatePublicationPlace(PublicationPlace publicationPlace);
}
