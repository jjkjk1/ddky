package com.systop.ddky.banner.service.impl;

import com.systop.ddky.banner.mapper.bannerMapper;
import com.systop.ddky.banner.service.bannerService;
import com.systop.ddky.common.pojo.Banner;
import com.systop.ddky.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class bannerServiceImpl implements bannerService {

    @Autowired
    private bannerMapper bannerMapper;
    @Override
    public R findBanners() {
        List<Banner> banners = bannerMapper.findBanners();
        return R.ok().put("banners",banners);
    }

    @Override
    public R findBannerById(Integer id) {
        Banner banner = bannerMapper.findBannerById(id);
        return R.ok().put("banner",banner);
    }

    @Override
    public R findBannerByName(String bannerName) {
        Banner name = bannerMapper.findBannerByName("%"+bannerName+"%");
        return R.ok().put("name",name);
    }

    @Override
    public R delectBannerById(Integer id) {
        Banner delbanner = bannerMapper.delectBannerById(id);
        return R.ok().put("delbanner",delbanner);
    }

    @Override
    public R saveBanner(Banner banner) {
        Integer saves = bannerMapper.saveBanner(banner);
        if (saves > 0){
            return R.ok("添加成功!");
        }else {
            return R.error("添加失败!");
        }
    }

    @Override
    public R updateBanner(Integer id, String bannerImage) {
        Integer updateBanner = bannerMapper.updateBanner(id,bannerImage);
        if (updateBanner > 0){
            return R.ok("更新成功!");
        }else {
            return R.error("更新失败!");
        }
    }


}
