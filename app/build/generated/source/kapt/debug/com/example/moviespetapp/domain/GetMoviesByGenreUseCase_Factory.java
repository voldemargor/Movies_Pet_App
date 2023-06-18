package com.example.moviespetapp.domain;

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
public final class GetMoviesByGenreUseCase_Factory implements Factory<GetMoviesByGenreUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetMoviesByGenreUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMoviesByGenreUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMoviesByGenreUseCase_Factory create(Provider<Repository> repositoryProvider) {
    return new GetMoviesByGenreUseCase_Factory(repositoryProvider);
  }

  public static GetMoviesByGenreUseCase newInstance(Repository repository) {
    return new GetMoviesByGenreUseCase(repository);
  }
}
