package com.ad4565.imagefinder.repository;

import com.ad4565.imagefinder.model_sql.WebsiteData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebsiteDataRepository extends CrudRepository<WebsiteData, Long> {

    Optional<WebsiteData> findByWebsiteTitle(String websiteName);

}
