package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.GetFavMoviesUseCase;

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
public final class GetFavMoviesUseCase_Factory implements Factory<GetFavMoviesUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetFavMoviesUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetFavMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetFavMoviesUseCase_Factory create(Provider<Repository> repositoryProvider) {
    return new GetFavMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetFavMoviesUseCase newInstance(Repository repository) {
    return new GetFavMoviesUseCase(repository);
  }
}
