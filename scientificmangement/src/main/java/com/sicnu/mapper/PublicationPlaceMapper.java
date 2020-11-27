package com.sicnu.mapper;

import com.sicnu.pojo.PublicationPlace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PublicationPlaceMapper {
    void addPublicationPlace(String pp_name);
    void delPublicationPlace(Integer pp_id);
    List<PublicationPlace> findAllPublicationPlace();
    PublicationPlace findPublicationPlaceByName(String pp_name);
    void updatePublicationPlace(PublicationPlace publicationPlace);
}
