package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.GetMainScreenFictionMoviesUseCase;

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
public final class GetMainScreenFictionMoviesUseCase_Factory implements Factory<GetMainScreenFictionMoviesUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetMainScreenFictionMoviesUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMainScreenFictionMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMainScreenFictionMoviesUseCase_Factory create(
      Provider<Repository> repositoryProvider) {
    return new GetMainScreenFictionMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetMainScreenFictionMoviesUseCase newInstance(Repository repository) {
    return new GetMainScreenFictionMoviesUseCase(repository);
  }
}
