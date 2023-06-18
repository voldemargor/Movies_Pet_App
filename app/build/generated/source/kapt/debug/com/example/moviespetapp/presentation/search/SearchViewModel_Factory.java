package com.example.moviespetapp.presentation.search;

import com.example.moviespetapp.domain.usecase.GetMoviesByGenreUseCase;
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
  private final Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider;

  public SearchViewModel_Factory(
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider) {
    this.getMoviesByGenreUseCaseProvider = getMoviesByGenreUseCaseProvider;
  }

  @Override
  public SearchViewModel get() {
    SearchViewModel instance = newInstance();
    SearchViewModel_MembersInjector.injectGetMoviesByGenreUseCase(instance, getMoviesByGenreUseCaseProvider.get());
    return instance;
  }

  public static SearchViewModel_Factory create(
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider) {
    return new SearchViewModel_Factory(getMoviesByGenreUseCaseProvider);
  }

  public static SearchViewModel newInstance() {
    return new SearchViewModel();
  }
}
