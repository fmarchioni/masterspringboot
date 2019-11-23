package com.masterspringboot.solrdemo;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Qualifier("userSolrRepo")
public interface SolrRepository extends SolrCrudRepository<CustomerDoc, String> {

    @Query(value = "*:*")
    List<CustomerDoc> getCustomers();

}