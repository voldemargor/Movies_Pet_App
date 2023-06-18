package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.GetMainScreenComedyMoviesUseCase;

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
public final class GetMainScreenComedyMoviesUseCase_Factory implements Factory<GetMainScreenComedyMoviesUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetMainScreenComedyMoviesUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMainScreenComedyMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMainScreenComedyMoviesUseCase_Factory create(
      Provider<Repository> repositoryProvider) {
    return new GetMainScreenComedyMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetMainScreenComedyMoviesUseCase newInstance(Repository repository) {
    return new GetMainScreenComedyMoviesUseCase(repository);
  }
}
