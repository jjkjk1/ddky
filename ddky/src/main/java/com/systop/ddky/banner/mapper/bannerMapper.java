package com.systop.ddky.banner.mapper;

import com.systop.ddky.common.pojo.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface bannerMapper {

    List<Banner> findBanners();

    Banner findBannerById(Integer id);

    Banner findBannerByName(String bannerName);

    Banner delectBannerById(Integer id);

    Integer saveBanner(Banner banner
    );

    Integer updateBanner(Integer id,String bannerImage);
}
