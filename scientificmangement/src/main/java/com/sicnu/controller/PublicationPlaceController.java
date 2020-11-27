package com.sicnu.controller;

import com.sicnu.pojo.PublicationPlace;
import com.sicnu.service.impl.PublicationPlaceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class PublicationPlaceController {
    @Resource
    PublicationPlaceImpl publicationPlaceService;

    private Result rs;

    @PostMapping("/publicationPlace/addPublicationPlace")
    public Result addPublicationPlace(String pp_name) {
        try {
            rs = publicationPlaceService.addPublicationPlace(pp_name);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/publicationPlace/delPublicationPlace")

    public Result delPublicationPlace(Integer pp_id) {

        try {
            rs = publicationPlaceService.delPublicationPlace(pp_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/publicationPlace/findAllPublicationPlace")

    public Result findAllPublicationPlace(String pp_name) {
        try {
            rs  = publicationPlaceService.findAllPublicationPlace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/publicationPlace/updatePublicationPlace")

    public Result updatePublicationPlace(PublicationPlace publicationPlace) {
        try {
            rs = publicationPlaceService.updatePublicationPlace(publicationPlace);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
