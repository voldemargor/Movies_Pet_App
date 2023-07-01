package com.example.moviespetapp.presentation.search;

import com.example.moviespetapp.domain.usecase.GetMoviesBySearchUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<GetMoviesBySearchUseCase> getMoviesBySearchUseCaseProvider;

  public SearchViewModel_Factory(
      Provider<GetMoviesBySearchUseCase> getMoviesBySearchUseCaseProvider) {
    this.getMoviesBySearchUseCaseProvider = getMoviesBySearchUseCaseProvider;
  }

  @Override
  public SearchViewModel get() {
    SearchViewModel instance = newInstance();
    SearchViewModel_MembersInjector.injectGetMoviesBySearchUseCase(instance, getMoviesBySearchUseCaseProvider.get());
    return instance;
  }

  public static SearchViewModel_Factory create(
      Provider<GetMoviesBySearchUseCase> getMoviesBySearchUseCaseProvider) {
    return new SearchViewModel_Factory(getMoviesBySearchUseCaseProvider);
  }

  public static SearchViewModel newInstance() {
    return new SearchViewModel();
  }
}
