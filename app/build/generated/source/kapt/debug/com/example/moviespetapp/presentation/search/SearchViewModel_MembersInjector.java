package com.example.moviespetapp.presentation.search;

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
public final class SearchViewModel_MembersInjector implements MembersInjector<SearchViewModel> {
  private final Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider;

  public SearchViewModel_MembersInjector(
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider) {
    this.getMoviesByGenreUseCaseProvider = getMoviesByGenreUseCaseProvider;
  }

  public static MembersInjector<SearchViewModel> create(
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider) {
    return new SearchViewModel_MembersInjector(getMoviesByGenreUseCaseProvider);
  }

  @Override
  public void injectMembers(SearchViewModel instance) {
    injectGetMoviesByGenreUseCase(instance, getMoviesByGenreUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.search.SearchViewModel.getMoviesByGenreUseCase")
  public static void injectGetMoviesByGenreUseCase(SearchViewModel instance,
      GetMoviesByGenreUseCase getMoviesByGenreUseCase) {
    instance.getMoviesByGenreUseCase = getMoviesByGenreUseCase;
  }
}
