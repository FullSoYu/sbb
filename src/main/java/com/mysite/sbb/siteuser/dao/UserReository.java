package com.mysite.sbb.siteuser.dao;

import com.mysite.sbb.siteuser.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReository extends JpaRepository<SiteUser, Long> {


}
