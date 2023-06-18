package com.example.moviespetapp.presentation.movieslist;

import com.example.moviespetapp.domain.usecase.GetMoviesByGenreUseCase;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MoviesListScreenViewModel_MembersInjector implements MembersInjector<MoviesListScreenViewModel> {
  private final Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider;

  public MoviesListScreenViewModel_MembersInjector(
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider) {
    this.getMoviesByGenreUseCaseProvider = getMoviesByGenreUseCaseProvider;
  }

  public static MembersInjector<MoviesListScreenViewModel> create(
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider) {
    return new MoviesListScreenViewModel_MembersInjector(getMoviesByGenreUseCaseProvider);
  }

  @Override
  public void injectMembers(MoviesListScreenViewModel instance) {
    injectGetMoviesByGenreUseCase(instance, getMoviesByGenreUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.movieslist.MoviesListScreenViewModel.getMoviesByGenreUseCase")
  public static void injectGetMoviesByGenreUseCase(MoviesListScreenViewModel instance,
      GetMoviesByGenreUseCase getMoviesByGenreUseCase) {
    instance.getMoviesByGenreUseCase = getMoviesByGenreUseCase;
  }
}
