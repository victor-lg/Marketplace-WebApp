package com.catalogue.repositories;

import org.springframework.data.repository.CrudRepository;

import com.catalogue.domains.Catalogue;

public interface CatalogueRepository extends CrudRepository<Catalogue, String> {
}
