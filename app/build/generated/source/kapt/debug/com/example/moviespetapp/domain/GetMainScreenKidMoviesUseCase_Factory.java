package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.GetMainScreenKidMoviesUseCase;

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
public final class GetMainScreenKidMoviesUseCase_Factory implements Factory<GetMainScreenKidMoviesUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetMainScreenKidMoviesUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMainScreenKidMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMainScreenKidMoviesUseCase_Factory create(
      Provider<Repository> repositoryProvider) {
    return new GetMainScreenKidMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetMainScreenKidMoviesUseCase newInstance(Repository repository) {
    return new GetMainScreenKidMoviesUseCase(repository);
  }
}
