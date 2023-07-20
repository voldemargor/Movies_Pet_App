package com.example.moviespetapp.presentation.search;

import com.example.moviespetapp.domain.usecase.GetMoviesBySearchUseCase;
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
  private final Provider<GetMoviesBySearchUseCase> getMoviesBySearchUseCaseProvider;

  public SearchViewModel_MembersInjector(
      Provider<GetMoviesBySearchUseCase> getMoviesBySearchUseCaseProvider) {
    this.getMoviesBySearchUseCaseProvider = getMoviesBySearchUseCaseProvider;
  }

  public static MembersInjector<SearchViewModel> create(
      Provider<GetMoviesBySearchUseCase> getMoviesBySearchUseCaseProvider) {
    return new SearchViewModel_MembersInjector(getMoviesBySearchUseCaseProvider);
  }

  @Override
  public void injectMembers(SearchViewModel instance) {
    injectGetMoviesBySearchUseCase(instance, getMoviesBySearchUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.search.SearchViewModel.getMoviesBySearchUseCase")
  public static void injectGetMoviesBySearchUseCase(SearchViewModel instance,
      GetMoviesBySearchUseCase getMoviesBySearchUseCase) {
    instance.getMoviesBySearchUseCase = getMoviesBySearchUseCase;
  }
}
