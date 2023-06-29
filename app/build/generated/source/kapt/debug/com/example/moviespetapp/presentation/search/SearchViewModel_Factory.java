package com.example.moviespetapp.presentation.search;

import com.example.moviespetapp.ThisApp;
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

  private final Provider<ThisApp> contextProvider;

  public SearchViewModel_Factory(
      Provider<GetMoviesBySearchUseCase> getMoviesBySearchUseCaseProvider,
      Provider<ThisApp> contextProvider) {
    this.getMoviesBySearchUseCaseProvider = getMoviesBySearchUseCaseProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public SearchViewModel get() {
    SearchViewModel instance = newInstance();
    SearchViewModel_MembersInjector.injectGetMoviesBySearchUseCase(instance, getMoviesBySearchUseCaseProvider.get());
    SearchViewModel_MembersInjector.injectContext(instance, contextProvider.get());
    return instance;
  }

  public static SearchViewModel_Factory create(
      Provider<GetMoviesBySearchUseCase> getMoviesBySearchUseCaseProvider,
      Provider<ThisApp> contextProvider) {
    return new SearchViewModel_Factory(getMoviesBySearchUseCaseProvider, contextProvider);
  }

  public static SearchViewModel newInstance() {
    return new SearchViewModel();
  }
}
