package dev.pucksmart;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID>
    extends ListCrudRepository<T, ID>, ListPagingAndSortingRepository<T, ID> {}
