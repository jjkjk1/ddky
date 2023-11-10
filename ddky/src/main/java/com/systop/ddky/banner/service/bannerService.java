package com.systop.ddky.banner.service;

import com.systop.ddky.common.pojo.Banner;
import com.systop.ddky.common.utils.R;

public interface bannerService {

    R findBanners();

    R findBannerById(Integer id);

    R findBannerByName(String bannerName);

    R delectBannerById(Integer id);

    R saveBanner(Banner banner);

    R updateBanner(Integer id,String bannerImage);
}
