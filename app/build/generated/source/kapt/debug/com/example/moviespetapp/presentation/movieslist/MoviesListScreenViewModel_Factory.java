package com.example.moviespetapp.presentation.movieslist;

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
public final class MoviesListScreenViewModel_Factory implements Factory<MoviesListScreenViewModel> {
  private final Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider;

  public MoviesListScreenViewModel_Factory(
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider) {
    this.getMoviesByGenreUseCaseProvider = getMoviesByGenreUseCaseProvider;
  }

  @Override
  public MoviesListScreenViewModel get() {
    MoviesListScreenViewModel instance = newInstance();
    MoviesListScreenViewModel_MembersInjector.injectGetMoviesByGenreUseCase(instance, getMoviesByGenreUseCaseProvider.get());
    return instance;
  }

  public static MoviesListScreenViewModel_Factory create(
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider) {
    return new MoviesListScreenViewModel_Factory(getMoviesByGenreUseCaseProvider);
  }

  public static MoviesListScreenViewModel newInstance() {
    return new MoviesListScreenViewModel();
  }
}
